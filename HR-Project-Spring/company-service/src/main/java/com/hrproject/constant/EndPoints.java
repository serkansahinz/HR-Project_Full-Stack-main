package com.hrproject.constant;

public class EndPoints {
  public static final String VERSION = "api/v1";
  public static final String COMPANY = VERSION + "/company";

  public static final String PENDING_COMPANIES = "/pending_companies";

  //Genel
  public static final String FIND_ALL = "/find_all";
  public static final String UPDATE = "/update";
  public static final String FIND_BY_ID = "/find_by_id";
  public static final String DELETE_BY_ID = "/delete_by_id";
  public static final String SAVE = "/save";

  ///User
  public static final String ACTIVATE_STATUS = "/activate_status";
  public static final String FIND_ALL_ACTIVE_COMPANIES = "/find_all_active_companies";
  public static final String FIND_COMPANY_BY_ID = "/find_company_by_id";
  public static final String UPDATE_COMPANY = "/update_company";
  public static final String GET_REMAINING_DAYS = "/get_remaining_days";
}
