package com.hrproject.constant;

public class EndPoints {
  public static final String VERSION = "api/v1";
  public static final String PAYMENT = VERSION + "/payment";

  public static final String PENDING_COMPANIES = "/pending_companies";

  //Genel
  public static final String FIND_ALL = "/find_all";
  public static final String UPDATE = "/update";
  public static final String FIND_BY_ID = "/find_by_id";
  public static final String DELETE_BY_ID = "/delete_by_id";
  public static final String SAVE = "/save";

  ///User
  public static final String ACTIVATE_STATUS = "/activate_status";
  public static final String CREATE_NEW_PAYMENT = "create_new_payment";
  public static final String GET_ALL_EXPENSE = "/get_all_expense";
  public static final String GET_ALL_INCOME = "/get_all_income";
  public static final String EMPLOYEE_CREATE_NEW_PAYMENT = "/employee_create_new_payment";
  public static final String GET_ALL_PENDING_PAYMENT = "/get_all_pending_payment";
  public static final String DELETE_EMPLOYEE_PAYMENT = "/delete_employee_payment";
  public static final String GET_ALL_PAYMENTS_BY_USER_ID = "/get_all_payments_by_user_id";
  public static final String UPLOAD_FILE = "/upload_file";
}
