import { createContext, useState, useEffect } from 'react';
// We CANNOT use useNavigate here directly if this provider is wrapping the Router.
// We will fix the navigation logic to be safer.

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Check if user is already logged in
    const token = localStorage.getItem('token');
    if (token) setUser({ token });
  }, []);

  const login = (token) => {
    localStorage.setItem('token', token);
    setUser({ token });
    // We will handle navigation inside the Login component, not here.
  };

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    // We will handle navigation inside the Navbar or component, not here.
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};