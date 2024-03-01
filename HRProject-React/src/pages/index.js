import React, { useContext, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import MainLayout from "../components/MainLayout";
import Dashboard from "./AuthPages/Dashboard";
import {
  BankOutlined,
  CalendarOutlined,
  CoffeeOutlined,
  CommentOutlined,
  DollarOutlined,
  FormOutlined,
  HomeOutlined,
  UserOutlined,
} from "@ant-design/icons";
import DashboardProject from "./ProjectPages/DashboardProject";
import CalendarProject from "./ProjectPages/Calendar";
import LeavePage from "./ProjectPages/LeavePage";
import EmployeeLayout from "../components/EmployeeLayout";
import AdminLayout from "../components/AdminLayout";
import GuestLayout from "../components/GuestLayout";
import CompanyApprove from "./AdminPages/CompanyApprove";
import CommmentApprove from "./AdminPages/CommentApprove";
import Employees from "./ProjectPages/Employees";
import EmployeeDashboard from "./EmployeePages/EmployeeDashboard";
import EmployeeInformation from "./EmployeePages/EmployeeInformation";
import CompanyLayout from "../components/CompanyLayout";
import Accounting from "./ProjectPages/Accounting";
import EmployeeComment from "./EmployeePages/EmployeeComments";
import DashboardGuest from "./GuestPages/DashboardGuest";
import Companies from "./GuestPages/Companies";
import CompanyDetail from "./GuestPages/CompanyDetail";
import jwt_decode from "jwt-decode";
import RoleContext from "../contexts/RoleContext";
import EmployeePermission from "./EmployeePages/EmployeePermission";
import CompanyComments from "./ProjectPages/CompanyComments";
import EmployeeAdvance from "./EmployeePages/EmployeeAdvance";
import EmployeePayment from "./EmployeePages/EmployeePayment";

const MainPage = ({ onChangeTheme, setPrimaryColor }) => {
  const { role, setRole } = useContext(RoleContext);

  useEffect(() => {
    if (localStorage.getItem("userToken") !== null) {
      const userToken = localStorage.getItem("userToken");
      const decodedToken = jwt_decode(userToken);
      setRole(decodedToken.role);
    }
  }, [role]);

  const companyMenu = [
    {
      key: "m1",
      icon: <HomeOutlined />,
      label: <Link to="/company/">Anasayfa</Link>,
    },
    {
      key: "m2",
      icon: <UserOutlined />,
      label: <Link to="/company/employee">Çalışanlar</Link>,
    },
    {
      key: "m3",
      icon: <DollarOutlined />,
      label: <Link to="/company/accounting">Muhasebe</Link>,
    },
    {
      key: "m4",
      icon: <CalendarOutlined />,
      label: <Link to="/company/calendar">Çalışma Takvimi</Link>,
    },
    {
      key: "m5",
      icon: <FormOutlined />,
      label: <Link to="/company/leave">İzin İşlemleri </Link>,
    },
    {
      key: "m6",
      icon: <CommentOutlined />,
      label: <Link to="/company/comments">Yorumlar </Link>,
    },
  ];
  const employeeMenu = [
    {
      key: "m1",
      icon: <HomeOutlined />,
      label: <Link to="/employee/">Anasayfa</Link>,
    },
    {
      key: "m2",
      icon: <UserOutlined />,
      label: <Link to="/employee/information">Şirket</Link>,
    },
    {
      key: "m3",
      icon: <CommentOutlined />,
      label: <Link to="/employee/comment">Yorumlar</Link>,
    },
    {
      key: "m4",
      icon: <CoffeeOutlined />,
      label: <Link to="/employee/permission">İzinler</Link>,
    },
    {
      key: "m5",
      icon: <DollarOutlined />,
      label: <Link to="/employee/advance">Avans</Link>,
    },
    {
      key: "m5",
      icon: <DollarOutlined />,
      label: <Link to="/employee/payment">Harcamalar</Link>,
    },
  ];
  const adminMenu = [
    {
      key: "m1",
      icon: <HomeOutlined />,
      label: <Link to="/admin/company">Şirket Onayları</Link>,
    },
    {
      key: "m2",
      icon: <BankOutlined />,
      label: <Link to="/admin/comment">Yorum Onayları</Link>,
    },
  ];
  const guestMenu = [
    {
      key: "m1",
      icon: <HomeOutlined />,
      label: <Link to="/guest/">Anasayfa</Link>,
    },
    {
      key: "m2",
      icon: <BankOutlined />,
      label: <Link to="/guest/companies">Şirketler</Link>,
    },
  ];

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            <MainLayout onChangeTheme={onChangeTheme}>
              <Routes>
                <Route index element={<Dashboard />} />
              </Routes>
            </MainLayout>
          }
        />
        {role === "COMPANY_MANAGER" && (
          <Route
            path="/company/*"
            element={
              <CompanyLayout onChangeTheme={onChangeTheme} menu={companyMenu}>
                <Routes>
                  <Route path="/" index element={<DashboardProject />} />
                  <Route path="/employee" index element={<Employees />} />
                  <Route path="/accounting" index element={<Accounting />} />
                  <Route path="/calendar" index element={<CalendarProject />} />
                  <Route path="/leave" index element={<LeavePage />} />
                  <Route path="/comments" index element={<CompanyComments />} />
                </Routes>
              </CompanyLayout>
            }
          />
        )}
        {(role === "COMPANY_MANAGER" || role === "EMPLOYEE") && (
          <Route
            path="/employee/*"
            element={
              <EmployeeLayout onChangeTheme={onChangeTheme} menu={employeeMenu}>
                <Routes>
                  <Route path="/" index element={<EmployeeDashboard />} />
                  <Route
                    path="/information"
                    index
                    element={<EmployeeInformation />}
                  />
                  <Route path="/comment" index element={<EmployeeComment />} />
                  <Route
                    path="/permission"
                    index
                    element={<EmployeePermission />}
                  />
                  <Route path="/advance" index element={<EmployeeAdvance />} />
                  <Route path="/payment" index element={<EmployeePayment />} />
                </Routes>
              </EmployeeLayout>
            }
          />
        )}
        <Route
          path="/admin/*"
          element={
            <AdminLayout onChangeTheme={onChangeTheme} menu={adminMenu}>
              <Routes>
                <Route path="/company" index element={<CompanyApprove />} />
                <Route path="/comment" index element={<CommmentApprove />} />
              </Routes>
            </AdminLayout>
          }
        />
        {role === "GUEST" && (
          <Route
            path="/guest/*"
            element={
              <GuestLayout onChangeTheme={onChangeTheme} menu={guestMenu}>
                <Routes>
                  <Route path="/" index element={<DashboardGuest />} />
                  <Route path="/companies" index element={<Companies />} />
                  <Route
                    path="/company/:id"
                    index
                    element={<CompanyDetail />}
                  />
                </Routes>
              </GuestLayout>
            }
          />
        )}
      </Routes>
    </Router>
  );
};

export default MainPage;
