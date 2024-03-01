import { useState } from "react";
import MainPage from "./pages/index";
import { ConfigProvider, theme } from "antd";
import { UserProvider } from "./contexts/UserContext";
import { RoleProvider } from "./contexts/RoleContext";

function App() {
  const [isDark, setIsDark] = useState(false);
  const [primaryColor, setPrimaryColor] = useState("#3a34eb");

  const onChangeTheme = (value) => {
    setIsDark(value);
  };
  return (
    <UserProvider>
      <RoleProvider>
        <ConfigProvider
          theme={{
            algorithm: theme[isDark ? "darkAlgorithm" : "defaultAlgorithm"],
            token: {
              colorPrimary: primaryColor,
              fontSize: 14,
            },
          }}
        >
          <MainPage
            onChangeTheme={onChangeTheme}
            setPrimaryColor={setPrimaryColor}
          />
        </ConfigProvider>
      </RoleProvider>
    </UserProvider>
  );
}

export default App;
