package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;
import java.util.List;

import com.reforcheck.backend.commons.entities.mysql.models.Unit;
import com.reforcheck.backend.commons.entities.nosql.models.Invoice;

public class ExternalWorker extends Worker implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Unit> listUnitDone;
	private List<Invoice> listInvoice;

	public List<Unit> getListUnitDone() {
		return listUnitDone;
	}

	public void setListUnitDone(List<Unit> listUnitDone) {
		this.listUnitDone = listUnitDone;
	}

	public List<Invoice> getListInvoice() {
		return listInvoice;
	}

	public void setListInvoice(List<Invoice> listInvoice) {
		this.listInvoice = listInvoice;
	}

}
