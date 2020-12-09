package com.reforcheck.backend.commons.entities.postgresql.models;

import java.io.Serializable;
import java.util.List;

import com.reforcheck.backend.commons.entities.nosql.models.Invoice;

public class Owner extends UserApp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Invoice> listInvoice;

	public List<Invoice> getListInvoice() {
		return listInvoice;
	}

	public void setListInvoice(List<Invoice> listInvoice) {
		this.listInvoice = listInvoice;
	}

}
