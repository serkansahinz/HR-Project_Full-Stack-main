import { createContext, useState } from "react";

const RoleContext = createContext();

const RoleProvider = ({ children }) => {
  const [role, setRole] = useState({});

  return (
    <RoleContext.Provider value={{ role, setRole }}>
      {children}
    </RoleContext.Provider>
  );
};

export default RoleContext;
export { RoleProvider };
