package com.hrproject.constant;

import com.hrproject.dto.request.EmployeeUpdateRequestDto;
import com.hrproject.dto.request.ManagerUpdateEmployeeRequestDto;
import com.hrproject.dto.response.UserProfileResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EndPoints {
  public static final String VERSION = "api/v1";
  public static final String USER = VERSION + "/user";
  public static final String ADVANCE = "/advance";

  //Genel
  public static final String FIND_ALL = "/find_all";
  public static final String MANAGER_UPDATE_EMPLOYEE = "/manager_update_employee";
  public static final String EMPLOYEE_UPDATE_PROFILE = "/employee_update";
  public static final String FIND_BY_ID = "/find_by_id";
  public static final String DELETE_BY_ID = "/delete_by_id";
  public static final String SAVE = "/save";

  public static final String FIND_EMPLOYEE_BY_COMPANY_ID = "/find_employee_by_company_id";

  ///User
  public static final String ACTIVATE_STATUS = "/activate_status";
  public static final String CREATE_ADVANCE = "/create_advance";
  public static final String FIND_PENDING_ADVANCE = "/find_pending_advance";
  public static final String FIND_ALL_ADVANCE_BY_USER_ID = "/find_all_advance_by_user_id";
  public static final String DELETE_ADVANCE_BY_ID = "/delete_advance_by_id";
  public static final String ACTIVATE_ADVANCE_STATUS = "/activate_advance_status";
  public static final String EMPLOYEE_UPDATE_AVATAR = "/employee_update_avatar";
}

