import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

function Navbar() {
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const isActive = (path) => location.pathname === path ? "text-blue-600 font-semibold" : "text-gray-600 hover:text-blue-500";

  return (
    <nav className="bg-white/80 backdrop-blur-md shadow-sm sticky top-0 z-50">
      <div className="container mx-auto px-6 py-4 flex justify-between items-center">
        <Link to="/" className="text-2xl font-bold bg-gradient-to-r from-blue-600 to-indigo-600 bg-clip-text text-transparent">
          FeedbackSystem
        </Link>
        
        <div className="flex gap-6 items-center">
          <Link to="/" className={isActive('/')}>Home</Link>
          <Link to="/products" className={isActive('/products')}>Products</Link>
          
          {user ? (
            <>
              <Link to="/dashboard" className={isActive('/dashboard')}>Dashboard</Link>
              <Link to="/feedback" className={isActive('/feedback')}>Feedback</Link>
              <button 
                onClick={handleLogout} 
                className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg transition-all shadow-md hover:shadow-lg text-sm font-medium"
              >
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/login" className={isActive('/login')}>Login</Link>
              <Link to="/signup" className="bg-blue-600 hover:bg-blue-700 text-white px-5 py-2 rounded-full transition-all shadow-md hover:shadow-lg text-sm font-medium">
                Get Started
              </Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}
export default Navbar;