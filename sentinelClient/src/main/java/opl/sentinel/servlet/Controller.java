package opl.sentinel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opl.sentinel.dao.SentinelContextDao;
import opl.sentinel.dao.impl.SentinelContextDaoImpl;
import opl.sentinel.domain.SentinelContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Controller extends HttpServlet {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SentinelContextDao
	 */
	private SentinelContextDao sentinelContextDAO;

	/**
	 * Constructor.
	 */
	public Controller() {
		this.sentinelContextDAO = new SentinelContextDaoImpl();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String action = String.valueOf(request.getParameter("action"));
		String jsonArray = "";
		String error = "";

		if (action.equals("list")) {
			// Get the sentinel list according the given parameters.
			try {

				int startPageIndex = Integer.parseInt(request
						.getParameter("jtStartIndex"));
				int recordsPerPage = Integer.parseInt(request
						.getParameter("jtPageSize"));

				List<SentinelContext> sentinelContextList = sentinelContextDAO
						.getProducersOffsetLimit(startPageIndex, recordsPerPage);

				int sentinelCount = sentinelContextDAO.countProducer();

				jsonArray = gson.toJson(sentinelContextList);

				jsonArray = this.onSuccess(jsonArray, sentinelCount);

			} catch (Exception ex) {
				error = this.onError(ex);
			}
		} else if (action.equals("getConsumers")){
			int id = Integer.parseInt(request
					.getParameter("id"));
			List<SentinelContext> consumerList = sentinelContextDAO.getConsumerByMessageOrigineId(id);
			jsonArray = gson.toJson(consumerList);
			jsonArray = this.onSuccess(jsonArray, 1);
			
		} else if (action.equals("getDetails")) {
			// Get the message details.
			int id = Integer.parseInt(request.getParameter("id"));
			SentinelContext sentinelContext = sentinelContextDAO.find(id);
			jsonArray = "[" + gson.toJson(sentinelContext) + "]";
			jsonArray = this.onSuccess(jsonArray, 1);
		}

		if (!"".equals(jsonArray)) {
			response.getWriter().print(jsonArray);
		} else {
			response.getWriter().println(error);
		}
	}

	/**
	 * Things to do on success.
	 *
	 * @param jsonArray
	 *            the resulting jsonArray.
	 * @param totalRecordCount
	 *            the number of total record count.
	 * @return the response to send.
	 */
	private String onSuccess(String jsonArray, int totalRecordCount) {
		return "{\"Result\":\"OK\",\"Records\":" + jsonArray
				+ ",\"TotalRecordCount\":" + totalRecordCount + "}";
	}

	/**
	 * Things to do on error.
	 *
	 * @param e
	 *            the thrown exception by the error.
	 * @return the response to send.
	 */
	private String onError(Exception e) {
		return "{\"Result\":\"ERROR\",\"Message\":" + e.getMessage() + "}";
	}

}
