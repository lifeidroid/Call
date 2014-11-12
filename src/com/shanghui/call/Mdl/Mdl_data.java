package com.shanghui.call.Mdl;

import java.util.ArrayList;
import java.util.List;

public class Mdl_data {
	private Mdl_app app;
	private List<Mdl_cell> call_banner = new ArrayList<Mdl_cell>();
	private List<Mdl_cell> main_banner = new ArrayList<Mdl_cell>();
	public Mdl_data() {
	}
	public Mdl_data(Mdl_app app, List<Mdl_cell> call_banner,
			List<Mdl_cell> main_banner) {
		super();
		this.app = app;
		this.call_banner = call_banner;
		this.main_banner = main_banner;
	}
	public Mdl_app getApp() {
		return app;
	}
	public void setApp(Mdl_app app) {
		this.app = app;
	}
	public List<Mdl_cell> getCall_banner() {
		return call_banner;
	}
	public void setCall_banner(List<Mdl_cell> call_banner) {
		this.call_banner = call_banner;
	}
	public List<Mdl_cell> getMain_banner() {
		return main_banner;
	}
	public void setMain_banner(List<Mdl_cell> main_banner) {
		this.main_banner = main_banner;
	}
	
}
