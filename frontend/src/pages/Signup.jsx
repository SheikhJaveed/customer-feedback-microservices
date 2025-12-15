import { useState } from 'react';
import api from '../api';
import { useNavigate, Link } from 'react-router-dom';

function Signup() {
  const [formData, setFormData] = useState({ username: '', email: '', password: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/auth/signup', formData);
      alert('Signup Successful! Please Login.');
      navigate('/login');
    } catch (err) {
      alert('Signup failed!');
    }
  };

  return (
    <div className="min-h-[80vh] flex items-center justify-center">
      <div className="bg-white p-8 rounded-2xl shadow-xl w-full max-w-md border border-gray-100">
        <h2 className="text-3xl font-bold text-gray-800 mb-2 text-center">Create Account</h2>
        <p className="text-gray-500 text-center mb-8">Join us to start sharing feedback.</p>
        
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Username</label>
            <input className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none" placeholder="johndoe" onChange={e => setFormData({...formData, username: e.target.value})} />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none" placeholder="john@example.com" onChange={e => setFormData({...formData, email: e.target.value})} />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Password</label>
            <input type="password" className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none" placeholder="••••••••" onChange={e => setFormData({...formData, password: e.target.value})} />
          </div>
          <button type="submit" className="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 rounded-lg shadow-md hover:shadow-lg transition-all mt-2">Sign Up</button>
        </form>
        <p className="mt-6 text-center text-sm text-gray-600">
          Already have an account? <Link to="/login" className="text-blue-600 hover:underline font-semibold">Log in</Link>
        </p>
      </div>
    </div>
  );
}
export default Signup;