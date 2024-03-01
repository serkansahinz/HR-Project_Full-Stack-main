import React from "react";

const Box = (props) => {
  const { title, btnClass, btnTitle, price, feature, onClickPricingButton } =
    props;

  const boxStyle = {
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-between",
    border: "1px solid #ccc",
    borderRadius: "8px",
    padding: "20px",
    margin: "10px",
    textAlign: "center",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
    height: "300px",
    flex: "1", // Eşit genişlik için
  };

  const titleStyle = {
    color: "#333",
    fontSize: "1.5rem",
    marginBottom: "10px",
  };

  const priceStyle = {
    color: "#007BFF",
    fontSize: "2rem",
    margin: "10px 0",
  };

  const buttonStyle = {
    backgroundColor: btnClass === "btn-primary" ? "#007BFF" : "transparent",
    color: btnClass === "btn-primary" ? "#fff" : "#007BFF",
    border: btnClass === "btn-primary" ? "none" : "1px solid #007BFF",
    padding: "10px",
    borderRadius: "4px",
    cursor: "pointer",
  };

  return (
    <div style={boxStyle}>
      <div style={titleStyle}>
        <h4>{title}</h4>
      </div>
      <div>
        <h1 style={priceStyle}>
          {price} <small>/ ay</small>
        </h1>
        <ul style={{ listStyle: "none" }}>
          {feature &&
            feature.map((data, index) => (
              <li style={{ fontSize: "22px" }} key={index}>
                {data}
              </li>
            ))}
        </ul>
        <button
          type="button"
          className={`btn btn-lg btn-block`}
          style={buttonStyle}
          onClick={onClickPricingButton}
        >
          {btnTitle}
        </button>
      </div>
    </div>
  );
};

export default Box;
