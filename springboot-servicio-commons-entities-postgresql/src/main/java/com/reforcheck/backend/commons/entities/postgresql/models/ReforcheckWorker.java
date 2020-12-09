package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;
import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.Reform;
import com.reforcheck.backend.commons.entities.nosql.models.Payroll;

public class ReforcheckWorker extends Worker implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Reform> listReformDone;
	private List<Payroll> listPayroll;

	public List<Reform> getListReformDone() {
		return listReformDone;
	}

	public void setListReformDone(List<Reform> listReformDone) {
		this.listReformDone = listReformDone;
	}

	public List<Payroll> getListPayroll() {
		return listPayroll;
	}

	public void setListPayroll(List<Payroll> listPayroll) {
		this.listPayroll = listPayroll;
	}

}
