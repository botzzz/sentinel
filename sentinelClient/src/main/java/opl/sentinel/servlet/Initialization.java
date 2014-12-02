package opl.sentinel.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opl.sentinel.dao.SentinelContextDao;
import opl.sentinel.dao.impl.SentinelContextDaoImpl;
import opl.sentinel.domain.FlowType;
import opl.sentinel.domain.SentinelContext;
import opl.sentinel.domain.StatusType;

import org.codehaus.plexus.util.ExceptionUtils;

public class Initialization extends HttpServlet {

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
	public Initialization() {
		this.sentinelContextDAO = new SentinelContextDaoImpl();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = String.valueOf(request.getParameter("action"));

		if (action.equals("init")){
			this.insertEntityList(50);
		}
//		else if (action.equals("dropall")){
//			// reading the user input
//			List<SentinelContext> sentinelContextList = sentinelContextDAO.findAll();
//			if(sentinelContextList != null && !sentinelContextList.isEmpty())
//			for(SentinelContext sentinelContext : sentinelContextList){
//				sentinelContextDAO.remove(sentinelContext.getId());
//			}
//		}
	}
	
	/**
	 * Insert a generated entity list (FOR TEST).
	 * @param count number of entity to insert.
	 */
	private void insertEntityList(int count){
		Random random = new Random();
		for(int i = 0; i < count; i++){
			
			// Create producer message
			SentinelContext producer = this.createEntity(String.valueOf(i), null);
			this.sentinelContextDAO.persist(producer);
			
			// Create consumer message
			int consumerCount = random.nextInt(2);
			for(int j = 0; j < consumerCount; j++){
				SentinelContext consumer = this.createEntity(i + " " + j, producer.getId());
				this.sentinelContextDAO.persist(consumer);
			}
		}
	}
	
	/**
	 * Create the entity.
	 * @return a create entity.
	 */
	private SentinelContext createEntity(String suffixe, Integer messageOrigineId){
		SentinelContext sentinelContext = new SentinelContext();
		sentinelContext.setErrorDate(new Date());
		sentinelContext.setErrorMessage("TEST : Message d'erreur"+suffixe);
		sentinelContext.setLoggedDate(new Date());
		sentinelContext.setMessageOrigine("TEST : Message d'origine"+suffixe);
		sentinelContext.setName("TEST : NOM"+suffixe);
		sentinelContext.setStatus(StatusType.values()[new Random().nextInt(StatusType.values().length)]);
		sentinelContext.setStackTrace(ExceptionUtils.getStackTrace(new NullPointerException()));
		sentinelContext.setMessageOrigineId(messageOrigineId);
		sentinelContext.setFlowType(messageOrigineId == null ? FlowType.PRODUCED : FlowType.CONSUMED);
		return sentinelContext;
	}
	
}
