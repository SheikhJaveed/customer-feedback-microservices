import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Navbar from './components/Navbar';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Dashboard from './pages/Dashboard';
import Feedback from './pages/Feedback';
import Products from './pages/Products';
import Home from './pages/Home'; // <--- 1. Import Home
import './App.css';

function App() {
  return (
    <Router>
      <AuthProvider>
        <Navbar />
        <div className="container mx-auto"> {/* Added mx-auto for centering */}
          <Routes>
            {/* 2. Use the Home Component here */}
            <Route path="/" element={<Home />} />
            
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/feedback" element={<Feedback />} />
            <Route path="/products" element={<Products />} />
          </Routes>
        </div>
      </AuthProvider>
    </Router>
  );
}
export default App;