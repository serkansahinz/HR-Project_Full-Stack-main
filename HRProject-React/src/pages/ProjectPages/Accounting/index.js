import React, { useContext, useEffect, useState } from "react";
import { Chart } from "primereact/chart";

import {
  Button,
  Col,
  DatePicker,
  Form,
  Modal,
  Row,
  Space,
  Statistic,
  Table,
} from "antd";
import {
  CheckCircleOutlined,
  DeleteOutlined,
  DownloadOutlined,
  PlusCircleOutlined,
} from "@ant-design/icons";
import AddPaymentForm from "./addPaymentForm";
import {
  activatePaymentEmployee,
  addPayment,
  deletePayment,
  deletePaymentEmployee,
  getChartDataByCompanyId,
  getExpensesByCompanyId,
  getIncomesByCompanyId,
  getPendingPayments,
} from "../../../services/payment";
import UserContext from "../../../contexts/UserContext";
import moment from "moment";
import {
  deleteAdvance,
  getPendingAdvances,
  setAdvanceStatusActive,
} from "../../../services/advance";

const Accounting = () => {
  const [expenses, setExpenses] = useState([]);
  const [incomes, setIncomes] = useState([]);
  const [advances, setAdvances] = useState([]);
  const [employeePayments, setEmployeePayments] = useState([]);

  const [isAddPaymentVisible, setIsAddPaymentVisible] = useState(false);
  const [chartData, setChartData] = useState({});
  const [chartOptions, setChartOptions] = useState({});
  const { user } = useContext(UserContext);
  const [countsObj, setCountsObj] = useState({});
  const [totalData, setTotalData] = useState({});
  const [filterDate, setFilterDate] = useState(null);
  const [upcomingExpenses, setUpcomingExpenses] = useState([]);

  const [addPaymentform] = Form.useForm();

  const applyFilter = (filterDate) => {
    const filteredExpenses = expenses.filter((expense) => {
      const paymentDate = moment(expense.paymentDate).format("YYYY-MM");
      if (filterDate === paymentDate) {
        return expense;
      } else {
        console.log("Date format ERROR");
      }
    });

    const filteredIncomes = incomes.filter((income) => {
      const paymentDate = moment(income.paymentDate).format("YYYY-MM");
      if (filterDate === paymentDate) {
        return income;
      } else {
        console.log("Date format ERROR");
      }
    });
    console.log("filteredExpenses:" + JSON.stringify(filteredExpenses));
    console.log("filteredIncomes:" + JSON.stringify(filteredIncomes));

    setExpenses(filteredExpenses);
    setIncomes(filteredIncomes);
  };

  const onDelete = (value) => {
    deletePayment(value.id).then((response) => {
      if (typeof response === "string") {
        alert(response);
      } else {
        setIncomes((prevState) => prevState.filter((t) => t.id !== value.id));
        setExpenses((prevState) => prevState.filter((t) => t.id !== value.id));
        setTotalData(expenses.length + incomes.length);
      }
    });
  };
  const openAddPayment = () => {
    setIsAddPaymentVisible(true);
  };
  const closeAddPayment = () => {
    setIsAddPaymentVisible(false);
  };
  const addPaymentOnFinish = (value) => {
    const updatedValue = { ...value, companyId: user.companyId };
    addPayment(updatedValue).then(() => {
      setIsAddPaymentVisible(false);
    });
    setTotalData(expenses.length + incomes.length);
    addPaymentform.resetFields();
  };

  const cancelDatePicker = () => {
    setTotalData({});
  };

  const approveAdvance = (id) => {
    setAdvanceStatusActive(id).then(() => {
      setAdvances((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

  const rejectAdvance = (id) => {
    deleteAdvance(id).then(() => {
      setAdvances((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

  const approveEmployeePayment = (id) => {
    activatePaymentEmployee(id).then(() => {
      setEmployeePayments((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

  const rejectEmployeePayment = (id) => {
    deletePaymentEmployee(id).then(() => {
      setEmployeePayments((prevState) => prevState.filter((t) => t.id !== id));
    });
  };

  const expenseColumns = [
    {
      title: "Gider Bilgisi",
      dataIndex: "paymentName",
      key: "t1",
    },
    {
      title: "Tutar",
      dataIndex: "amount",
      key: "t2",
    },
    {
      title: "Para Birimi",
      dataIndex: "currency",
      key: "t3",
    },
    {
      title: "Ödeme Tarihi",
      dataIndex: "paymentDate",
      key: "t4",
    },
    {
      title: "Fatura Açıklama",
      dataIndex: "paymentDetail",
      key: "t5",
    },
    {
      title: "Fatura Dosyası",
      dataIndex: "fileUrl",
      key: "t6",
      render: (text) =>
        text ? (
          <a href={text} target="_blank" rel="noopener noreferrer">
            <DownloadOutlined /> Dosyayı İndir
          </a>
        ) : null,
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t7",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => onDelete(row)}
            danger
          />
        </Space>
      ),
    },
  ];
  const incomeColumns = [
    {
      title: "Gelir Bilgisi",
      dataIndex: "paymentName",
      key: "t1",
    },
    {
      title: "Tutar",
      dataIndex: "amount",
      key: "t2",
    },
    {
      title: "Para Birimi",
      dataIndex: "currency",
      key: "t3",
    },
    {
      title: "Ödeme Tarihi",
      dataIndex: "paymentDate",
      key: "t4",
    },
    {
      title: "Fatura Açıklama",
      dataIndex: "paymentDetail",
      key: "t5",
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t6",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => onDelete(row)}
            danger
          />
        </Space>
      ),
    },
  ];
  const advanceColumns = [
    {
      title: "Kişi",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "Avans Miktarı",
      dataIndex: "amount",
      key: "t2",
    },
    {
      title: "Para Birimi",
      dataIndex: "amountType",
      key: "t2",
      render: (text) => <span>{text} TL</span>,
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t3",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<CheckCircleOutlined />}
            onClick={() => approveAdvance(row.id)}
            size="small"
          />
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => rejectAdvance(row.id)}
            danger
          />
        </Space>
      ),
    },
  ];
  const employeePaymentColumns = [
    {
      title: "Kişi",
      dataIndex: "fullName",
      key: "t1",
    },
    {
      title: "Ödeme İsmi",
      dataIndex: "paymentName",
      key: "t2",
    },
    {
      title: "Tutar",
      dataIndex: "amount",
      key: "t3",
    },
    {
      title: "Para Birimi",
      dataIndex: "currency",
      key: "t4",
    },
    {
      title: "Talep Tarihi",
      dataIndex: "createdDate",
      key: "t5",
    },
    {
      title: "Fatura Açıklama",
      dataIndex: "paymentDetail",
      key: "t6",
    },
    {
      title: "Fatura Dosyası",
      dataIndex: "fileUrl",
      key: "t7",
      render: (text) =>
        text ? (
          <a href={text} target="_blank" rel="noopener noreferrer">
            <DownloadOutlined /> Dosyayı İndir
          </a>
        ) : null,
    },
    {
      title: "Action",
      dataIndex: "id",
      key: "t8",
      width: "100px",
      render: (cell, row) => (
        <Space>
          <Button
            type="primary"
            shape="circle"
            icon={<CheckCircleOutlined />}
            onClick={() => approveEmployeePayment(row.id)}
            size="small"
          />
          <Button
            type="primary"
            shape="circle"
            icon={<DeleteOutlined />}
            size="small"
            onClick={() => rejectEmployeePayment(row.id)}
            danger
          />
        </Space>
      ),
    },
  ];

  const fetchExpenses = async () => {
    await getExpensesByCompanyId(user.companyId).then((response) => {
      setExpenses(response);
    });
  };

  const fetchIncomes = async () => {
    await getIncomesByCompanyId(user.companyId).then((response) => {
      setIncomes(response);
    });
  };

  const fetchChart = async () => {
    await getChartDataByCompanyId(user.companyId).then((response) => {
      setCountsObj(response);
      const counts = Object.values(response);
      const maxCount = Math.max(...counts);
      const yAxisMax = maxCount * 1.2;

      const data = {
        labels: ["Toplam Kazanç", "Toplam Ödeme", "Kar-Zarar"],
        datasets: [
          {
            label: "Tutar",
            data: counts,
            backgroundColor: "blue",
            borderColor: "blue",
            borderWidth: 3,
          },
        ],
      };
      const options = {
        scales: {
          y: {
            beginAtZero: true,
            max: yAxisMax,
          },
        },
      };
      setChartData(data);
      setChartOptions(options);
    });
  };

  useEffect(() => {
    getPendingAdvances(user.companyId).then((response) => {
      setAdvances(response);
    });
  }, [user]);

  useEffect(() => {
    getPendingPayments(user.companyId).then((response) => {
      setEmployeePayments(response);
    });
  }, [user]);

  useEffect(() => {
    const fetchData = async () => {
      fetchExpenses();
      fetchIncomes();
      fetchChart();
    };
    fetchData();
  }, [totalData, user, employeePayments]);

  useEffect(() => {
    const currentDate = moment();
    const upcomingDate = moment(currentDate).add(15, "days");
    getExpensesByCompanyId(user.companyId).then((expenses) => {
      const filteredExpenses = expenses.filter((expense) => {
        const paymentDate = moment(expense.paymentDate);
        return paymentDate.isBetween(currentDate, upcomingDate);
      });
      setUpcomingExpenses(filteredExpenses);
    });
  }, [user, totalData]);

  return (
    <Space className="companies" direction="vertical">
      <DatePicker.MonthPicker
        placeholder="Filtreleme Tarihi Seçin"
        onChange={(originalDate) => {
          if (originalDate) {
            setFilterDate(" ");
            const year = originalDate ? originalDate.$y : "";
            const month =
              originalDate.$M < 10
                ? `0${originalDate.$M + 1}`
                : originalDate.$M + 1;
            const formattedDate = `${year}-${month}`;
            setFilterDate(formattedDate);
            applyFilter(formattedDate);
          } else {
            cancelDatePicker();
          }
        }}
        style={{ marginBottom: "16px" }}
      />
      <Row gutter={16}>
        <Col span={8}>
          <Statistic title="Toplam Gelir" value={countsObj.totalIncome} />
        </Col>
        <Col span={8}>
          <Statistic title="Toplam Gider" value={countsObj.totalExpense} />
        </Col>
        <Col span={8}>
          <Statistic title="Kar-Zarar" value={countsObj.netValue} />
        </Col>
      </Row>
      <Row>
        <Chart
          style={{ width: "40%" }}
          width="600px"
          type="bar"
          data={chartData}
          options={chartOptions}
        />

        <Table
          style={{ paddingLeft: "80px" }}
          dataSource={upcomingExpenses}
          columns={incomeColumns}
        />
      </Row>

      <Button
        type="primary"
        shape="circle"
        icon={<PlusCircleOutlined />}
        size="large"
        onClick={openAddPayment}
      />
      <Table dataSource={expenses} columns={expenseColumns} />
      <Table dataSource={incomes} columns={incomeColumns} />
      <h1>Personel Avans Talepleri</h1>
      <Table dataSource={advances} columns={advanceColumns} />
      <h1>Personel Harcama Talepleri</h1>
      <Table dataSource={employeePayments} columns={employeePaymentColumns} />
      <Modal
        title="Ödeme Kayıt Formu"
        open={isAddPaymentVisible}
        onCancel={closeAddPayment}
        footer={null}
      >
        <AddPaymentForm form={addPaymentform} onFinish={addPaymentOnFinish} />
      </Modal>
    </Space>
  );
};

export default Accounting;
