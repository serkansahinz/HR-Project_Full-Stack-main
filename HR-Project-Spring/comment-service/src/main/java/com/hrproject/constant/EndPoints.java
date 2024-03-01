package com.hrproject.constant;

public class EndPoints {
  public static final String VERSION = "api/v1";
  public static final String COMMENT = VERSION + "/comment";

  public static final String PENDING_COMMENTS = "/pending_comments";

  //Genel
  public static final String FIND_ALL = "/find_all";
  public static final String UPDATE = "/update";
  public static final String FIND_BY_ID = "/find_by_id";
  public static final String DELETE_BY_ID = "/delete_by_id";
  public static final String SAVE = "/save";

  ///User
  public static final String ACTIVATE_STATUS = "/activate_status";
  public static final String CREATE_COMMENT = "/create_comment";

  public static final String FIND_ALL_ACTIVE_COMMENTS_BY_COMPANY_ID = "/find_all_active_comments_by_company_id";
  public static final String FIND_ALL_ACTIVE_COMMENTS_BY_USER_ID = "/find_all_active_comments_by_user_id";
  public static final String FIND_ALL_PENDING_COMMENTS_BY_USER_ID = "/find_all_pending_comments_by_user_id";
  public static final String FIND_ALL_BY_USER_ID = "/find_all_by_user_id";
  public static final String FIND_ALL_PENDING_COMMENTS = "/find_all_pending_comments";
  public static final String DELETE_COMMENT = "/delete_comment";
  public static final String ACTIVATE_COMMENT = "/activate_comment";
}

