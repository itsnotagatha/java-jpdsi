package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String lata;
	private String procent;
	private Double result;



	public String getKwota() {
		return kwota;
	}
	public void setKwota(String kwota) {
		this.kwota = kwota;
	}
	public String getLata() {
		return lata;
	}
	public void setLata(String lata) {
		this.lata = lata;
	}
	public String getProcent() {
		return procent;
	}
	public void setProcent(String procent) {
		this.procent = procent;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	
	
	public boolean doTheMath() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lata = Double.parseDouble(this.lata);
			double procent = Double.parseDouble(this.procent);

			result = kwota * (procent/12) / (1-(1/Math.pow(1+procent/12, lata*12)));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas przetwarzania parametr�w", null));
			return false;
		}
	}
	
	@Inject
	FacesContext ctx;


	//obliczenia kredytu
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

}