package com.hrproject.constant;

public class EndPoints {
  public static final String VERSION = "api/v1";
  public static final String PERMISSION = VERSION + "/permission";

  //Genel
  public static final String FIND_ALL = "/find_all";
  public static final String CREATE_PERMISSION = "/create_permission";
  public static final String EMPLOYEE_UPDATE_PROFILE = "/employee_update";
  public static final String FIND_BY_ID = "/find_by_id";
  public static final String DELETE_BY_ID = "/delete_by_id";
  public static final String SAVE = "/save";

  public static final String FIND_EMPLOYEE_BY_COMPANY_ID = "/find_employee_by_company_id";

  ///User
  public static final String ACTIVATE_STATUS = "/activate_status";
  public static final String FIND_PENDING_PERMISSIONS_BY_COMPANY_ID = "/find_pending_permissions_by_company_id";
  public static final String FIND_PERMISSIONS_BY_USER_ID = "/find_permissions_by_user_id";
  public static final String FIND_REMAINING_PERMISSIONS_BY_USER_ID =  "/find_remaining_permissions_by_user_id";
  ;
}

