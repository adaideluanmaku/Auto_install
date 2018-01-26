package com.ch.sys;


public class Files_config {
	String jdbc_url=null;
	String jdbc_username=null;
	String jdbc_password=null;
	String seccache_dir=null;
	String cache_dir=null;
	String redis_host=null;
	String redis_pass=null;
	String redis_default_db=null;
	String this_PassWebServiceUrl=null;
	String passrhap_path=null;
	String passcore_path=null;
	String birt_savepath=null;
	String AA_port=null;
	String passaa_path=null;
	String passtcm_path=null;
	String jdbc_url_oracle=null;
	String jdbc_username_oracle=null;
	String jdbc_password_oracle=null;
	String passrhmm_url=null;
	
	public String getPassrhmm_url() {
		return passrhmm_url;
	}
	public void setPassrhmm_url(String passrhmm_url) {
		this.passrhmm_url = passrhmm_url;
	}
	public String getJdbc_url_oracle() {
		return jdbc_url_oracle;
	}
	public void setJdbc_url_oracle(String jdbc_url_oracle) {
		this.jdbc_url_oracle = jdbc_url_oracle;
	}
	public String getJdbc_username_oracle() {
		return jdbc_username_oracle;
	}
	public void setJdbc_username_oracle(String jdbc_username_oracle) {
		this.jdbc_username_oracle = jdbc_username_oracle;
	}
	public String getJdbc_password_oracle() {
		return jdbc_password_oracle;
	}
	public void setJdbc_password_oracle(String jdbc_password_oracle) {
		this.jdbc_password_oracle = jdbc_password_oracle;
	}
	public String getPasstcm_path() {
		return passtcm_path;
	}
	public void setPasstcm_path(String passtcm_path) {
		this.passtcm_path = passtcm_path;
	}
	public String getPassaa_path() {
		return passaa_path;
	}
	public void setPassaa_path(String passaa_path) {
		this.passaa_path = passaa_path;
	}
	public String getPassrhap_path() {
		return passrhap_path;
	}
	public void setPassrhap_path(String passrhap_path) {
		this.passrhap_path = passrhap_path;
	}
	public String getPasscore_path() {
		return passcore_path;
	}
	public void setPasscore_path(String passcore_path) {
		this.passcore_path = passcore_path;
	}
	public String getBirt_savepath() {
		return birt_savepath;
	}
	public void setBirt_savepath(String birt_savepath) {
		this.birt_savepath = birt_savepath;
	}
	public String getAA_port() {
		return AA_port;
	}
	public void setAA_port(String aA_port) {
		AA_port = aA_port;
	}
	public String getThis_PassWebServiceUrl() {
		return this_PassWebServiceUrl;
	}
	public void setThis_PassWebServiceUrl(String this_PassWebServiceUrl) {
		this.this_PassWebServiceUrl = "\""+this_PassWebServiceUrl+"\";";
	}
	public String getJdbc_url() {
		return jdbc_url;
	}
	public void setJdbc_url(String url) {
		
		this.jdbc_url = url;
	}
	public String getJdbc_username() {
		return jdbc_username;
	}
	public void setJdbc_username(String jdbc_username) {
		this.jdbc_username = jdbc_username;
	}
	public String getJdbc_password() {
		return jdbc_password;
	}
	public void setJdbc_password(String jdbc_password) {
		this.jdbc_password = jdbc_password;
	}
	public String getSeccache_dir() {
		return seccache_dir;
	}
	public void setSeccache_dir(String seccache_dir) {
		this.seccache_dir = seccache_dir;
	}
	public String getCache_dir() {
		return cache_dir;
	}
	public void setCache_dir(String cache_dir) {
		this.cache_dir = cache_dir;
	}
	public String getRedis_host() {
		return redis_host;
	}
	public void setRedis_host(String redis_host) {
		this.redis_host = redis_host;
	}
	public String getRedis_pass() {
		return redis_pass;
	}
	public void setRedis_pass(String redis_pass) {
		this.redis_pass = redis_pass;
	}
	public String getRedis_default_db() {
		return redis_default_db;
	}
	public void setRedis_default_db(String redis_default_db) {
		this.redis_default_db = redis_default_db;
	}
	
}
