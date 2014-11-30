/**
 *
 */
package sentinel.context;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author buissartt
 *
 */
@Entity
@Table(name = "SENTINEL_CONTEXT")
public class SentinelContext implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3479841400563625602L;

	@Id
	@Column(name = "SC_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "SC_NAME")
	private String name;

	@Column(name = "SC_MESSAGE_ORIGINE", columnDefinition = "mediumtext")
	private String messageOrigine;

	@Column(name = "SC_SOURCE")
	private String source;

	@Column(name = "SC_DESTINATION")
	private String destination;

	@Column(name = "SC_LOGGED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loggedDate;

	@Column(name = "SC_ERROR_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date errorDate;

	@Column(name = "SC_ERROR_MESSAGE")
	private String errorMessage;

	@Column(name = "SC_STACKTRACE", columnDefinition = "mediumtext")
	private String stackTrace;

	@Column(name = "SC_STATUS")
	@Enumerated(EnumType.STRING)
	private StatusType status;

	@Column(name = "SC_FLOW_TYPE")
	@Enumerated(EnumType.STRING)
	private FlowType flowType;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the loggedDate
	 */
	public Date getLoggedDate() {
		return loggedDate;
	}

	/**
	 * @param loggedDate
	 *            the loggedDate to set
	 */
	public void setLoggedDate(Date loggedDate) {
		this.loggedDate = loggedDate;
	}

	/**
	 * @return the errorDate
	 */
	public Date getErrorDate() {
		return errorDate;
	}

	/**
	 * @param errorDate
	 *            the errorDate to set
	 */
	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace() {
		return stackTrace;
	}

	/**
	 * @param stackTrace
	 *            the stackTrace to set
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the messageOrigine
	 */
	public String getMessageOrigine() {
		return messageOrigine;
	}

	/**
	 * @param messageOrigine
	 *            the messageOrigine to set
	 */
	public void setMessageOrigine(String messageOrigine) {
		this.messageOrigine = messageOrigine;
	}

	@PrePersist
	protected void onCreate() {
		loggedDate = new Date();
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the flowType
	 */
	public FlowType getFlowType() {
		return flowType;
	}

	/**
	 * @param flowType
	 *            the flowType to set
	 */
	public void setFlowType(FlowType flowType) {
		this.flowType = flowType;
	}

}
