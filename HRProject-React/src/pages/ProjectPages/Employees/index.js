import React, { useContext, useEffect, useState } from "react";
import {
  Card,
  Pagination,
  Input,
  Select,
  Button,
  Modal,
  Row,
  Col,
  Space,
  Form,
} from "antd";
import {
  CloseOutlined,
  EditOutlined,
  MailOutlined,
  PhoneOutlined,
} from "@ant-design/icons";
import EmployeeEditForm from "./editForm";
import EmployeeCreateForm from "./createForm";
import {
  addEmployee,
  deleteEmployee,
  getEmployeesByCompanyId,
  updateEmployeeByManager,
} from "../../../services/employee";
import UserContext from "../../../contexts/UserContext";

const { Search } = Input;
const { Option } = Select;

const pageSize = 10;

const Employees = () => {
  const [currentPage, setCurrentPage] = useState(1);

  const [searchText, setSearchText] = useState("");
  const [selectedDepartment, setSelectedDepartment] = useState("");

  const [isProfileVisible, setProfileVisible] = useState(false);
  const [isDeleteModalVisible, setDeleteModalVisible] = useState(false);
  const [isProfileEditVisible, setProfileEditVisible] = useState(false);
  const [employees, setEmployees] = useState([]);
  const [filteredData, setFilteredData] = useState(employees);
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const { user } = useContext(UserContext);

  const [createForm] = Form.useForm();

  const getUniqueDepartments = (employees) => {
    const departments = new Set();
    employees.forEach((employee) => {
      departments.add(employee.department);
    });
    return Array.from(departments);
  };

  const handleProfileClose = () => {
    setProfileVisible(false);
  };
  const handleProfileEditClose = () => {
    setProfileEditVisible(false);
  };
  const handleDeleteClose = () => {
    setDeleteModalVisible(false);
  };

  const handleProfileClick = () => {
    setProfileVisible(true);
  };

  const handleSearch = (text) => {
    setSearchText(text);
    setCurrentPage(1); // Arama yapıldığında ilk sayfaya dön
    filterData(text, selectedDepartment);
  };

  const handleDepartmentFilter = (value) => {
    setSelectedDepartment(value);
    setCurrentPage(1); // Departman filtreleme yapıldığında ilk sayfaya dön
    filterData(searchText, value);
  };

  const onEditEmployee = (employee) => {
    setSelectedEmployee(employee);
    setProfileEditVisible(true);
  };

  const filterData = (text, department) => {
    const filtered = employees.filter(
      (employee) =>
        employee.name.toLowerCase().includes(text.toLowerCase()) &&
        (department === "" || employee.department === department)
    );
    setFilteredData(filtered);
  };

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const deleteSelectedEmployee = () => {
    console.log("ID:" + selectedEmployee.authId);
    deleteEmployee(selectedEmployee.authId);
    setDeleteModalVisible(false);
  };

  const openDeleteConfirmation = (employee) => {
    setSelectedEmployee(employee);
    setDeleteModalVisible(true);
  };

  const onFinishEdit = (values) => {
    const updatedEmployee = { ...values, id: selectedEmployee.id };
    updateEmployeeByManager(updatedEmployee);
    setSelectedEmployee(" ");
    setProfileEditVisible(false);
  };

  const onFinishCreateProfile = (values) => {
    addEmployee({
      ...values,
      companyId: user.companyId,
      companyName: user.companyName,
    });

    createForm.resetFields();
    setProfileVisible(false);
  };

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await getEmployeesByCompanyId(user.companyId);
        setEmployees(response);
      } catch (error) {
        console.error("Çalışanları getirirken hata oluştu:", error);
      }
    };

    if (user && user.companyId) {
      fetchEmployees();
    }
  }, [
    user,
    selectedEmployee,
    isProfileVisible,
    isDeleteModalVisible,
    employees,
  ]);

  useEffect(() => {
    // employees dizisi değiştiğinde filteredData state'ini güncelle
    setFilteredData(
      employees.filter(
        (employee) =>
          employee.name.toLowerCase().includes(searchText.toLowerCase()) &&
          (selectedDepartment === "" ||
            employee.department === selectedDepartment)
      )
    );
  }, [employees, searchText, selectedDepartment, isDeleteModalVisible]);

  const startIndex = (currentPage - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  const currentEmplooyes = filteredData.slice(startIndex, endIndex);

  return (
    <div style={{ minWidth: "1000px" }}>
      <h1 style={{ marginBottom: "15px", textAlign: "center" }}>
        Çalışan Listesi
      </h1>
      <div
        style={{
          gap: "20px",
          display: "flex",
          justifyContent: "center",
          marginBottom: 16,
        }}
      >
        <Search
          placeholder="Çalışan Ara"
          onSearch={handleSearch}
          style={{ width: 200, marginRight: 10 }}
        />
        <Select
          style={{ width: 150 }}
          placeholder="Departman Filtrele"
          onChange={handleDepartmentFilter}
        >
          <Option value="">Tüm Departmanlar</Option>
          {getUniqueDepartments(employees).map((department) => (
            <Option key={department} value={department}>
              {department}
            </Option>
          ))}
        </Select>
        <Button onClick={handleProfileClick}>Çalışan Ekle</Button>
      </div>
      <Row
        style={{
          display: "flex",
          gap: "30px",
          justifyContent: "center",
        }}
        gutter={16}
      >
        {currentEmplooyes.map((employee) => (
          <Col
            style={{
              display: "flex",
              flex: "0 0 auto",
            }}
            key={employee.id}
            span={8}
          >
            <Card
              style={{
                marginBottom: 16,
                borderRadius: 10,
                boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
                width: "270px",
                height: "500px",
                display: "flex",
                flexDirection: "column",
              }}
              cover={
                <div
                  style={{
                    width: "200px",
                    height: "200px",
                    borderRadius: "50%",
                    overflow: "hidden",
                    margin: "0 auto",
                    marginTop: "10px",
                  }}
                >
                  <img
                    alt={employee.name}
                    src={employee.avatar}
                    style={{
                      width: "100%",
                      height: "100%",
                      objectFit: "cover",
                    }}
                  />
                </div>
              }
              extra={
                <Col>
                  <Button
                    type="text"
                    icon={<EditOutlined />}
                    onClick={() => onEditEmployee(employee)}
                  />
                  <Button
                    type="text"
                    icon={<CloseOutlined />}
                    onClick={() => openDeleteConfirmation(employee)}
                  />
                </Col>
              }
            >
              <Space direction="vertical" size="small" style={{ flex: 1 }}>
                <h2 style={{ color: "#1890ff", textAlign: "center" }}>
                  {employee.name}
                </h2>
                <p style={{ textAlign: "center" }}>{employee.status}</p>
                <hr style={{ width: "220px" }} />
                <div style={{ color: "#1890ff", flex: 1 }}>
                  <Space direction="vertical" style={{}}>
                    <Space align="baseline">
                      <span>Departman:</span>
                      <span>{employee.department}</span>
                    </Space>
                  </Space>
                </div>
                <div>
                  <div>
                    <MailOutlined /> {employee.email}
                  </div>
                  <div>
                    <PhoneOutlined /> {employee.phone}
                  </div>
                </div>
              </Space>
            </Card>
          </Col>
        ))}
      </Row>

      <Modal
        title="Personel Kayıt Formu"
        open={isProfileVisible}
        onOk={handleProfileClose}
        onCancel={handleProfileClose}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
      >
        <EmployeeCreateForm
          onFinish={onFinishCreateProfile}
          form={createForm}
        ></EmployeeCreateForm>
      </Modal>
      <Modal
        title="Personel Düzenle"
        open={isProfileEditVisible}
        onOk={handleProfileEditClose}
        onCancel={handleProfileEditClose}
        cancelButtonProps={{ style: { display: "none" } }}
        okButtonProps={{ style: { display: "none" } }}
        destroyOnClose
      >
        <EmployeeEditForm
          onFinish={onFinishEdit}
          initialValues={selectedEmployee}
        ></EmployeeEditForm>
      </Modal>

      <Modal
        title="Çalışan Sil"
        open={isDeleteModalVisible}
        onCancel={handleDeleteClose}
        footer={[
          <Button key="hayir" onClick={handleDeleteClose}>
            Hayır
          </Button>,
          <Button key="evet" type="primary" onClick={deleteSelectedEmployee}>
            Evet
          </Button>,
        ]}
      >
        Çalışanı silmek istediğinize emin misiniz?
      </Modal>
      <Pagination
        showSizeChanger
        showQuickJumper
        defaultPageSize={pageSize}
        current={currentPage}
        total={filteredData.length}
        onChange={handlePageChange}
        style={{ marginTop: 20, textAlign: "center" }}
      />
    </div>
  );
};

export default Employees;
