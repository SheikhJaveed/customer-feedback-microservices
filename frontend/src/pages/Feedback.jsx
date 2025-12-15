import { useState, useEffect } from 'react';
import api from '../api';

function Feedback() {
  const [feedbacks, setFeedbacks] = useState([]);
  const [products, setProducts] = useState([]); // Store products for dropdown
  const [formData, setFormData] = useState({ message: '', rating: 5, productId: '' });

  const loadFeedback = () => {
    api.get('/feedback').then(res => setFeedbacks(res.data));
  };

  const loadProducts = () => {
    api.get('/products').then(res => setProducts(res.data));
  };

  useEffect(() => {
    loadFeedback();
    loadProducts();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!formData.productId) {
      alert("Please select a product!");
      return;
    }
    
    try {
      await api.post('/feedback', {
        message: formData.message,
        rating: formData.rating,
        productId: Number(formData.productId)
      });
      setFormData({ message: '', rating: 5, productId: '' });
      loadFeedback();
      alert("Feedback Submitted!");
    } catch (err) {
      console.error(err);
      alert("Failed to submit feedback.");
    }
  };

  return (
    <div className="container mx-auto px-6 py-8 max-w-4xl">
      <div className="mb-8 text-center">
        <h1 className="text-3xl font-bold text-gray-800">Customer Feedback</h1>
        <p className="text-gray-500 mt-2">Share your experience with our products.</p>
      </div>

      {/* Input Form */}
      <div className="bg-white p-6 rounded-2xl shadow-lg border border-gray-100 mb-10">
        <form onSubmit={handleSubmit}>
          
          {/* Product Selection */}
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">Select Product</label>
            <select 
              value={formData.productId} 
              onChange={e => setFormData({...formData, productId: e.target.value})}
              className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none bg-white"
              required
            >
              <option value="">-- Choose a Product --</option>
              {products.map(p => (
                <option key={p.id} value={p.id}>{p.name} (${p.price})</option>
              ))}
            </select>
          </div>

          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">Your Rating</label>
            <div className="flex gap-2">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  type="button"
                  key={star}
                  onClick={() => setFormData({...formData, rating: star})}
                  className={`text-2xl transition-transform hover:scale-110 ${star <= formData.rating ? 'text-yellow-400' : 'text-gray-300'}`}
                >
                  ★
                </button>
              ))}
            </div>
          </div>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">Your Message</label>
            <textarea 
              value={formData.message} 
              onChange={e => setFormData({...formData, message: e.target.value})} 
              placeholder="Tell us about your experience..." 
              className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none h-32 resize-none"
              required 
            />
          </div>
          <button type="submit" className="bg-indigo-600 hover:bg-indigo-700 text-white px-6 py-2 rounded-lg font-medium transition-all shadow-md w-full">
            Submit Review
          </button>
        </form>
      </div>

      {/* Feedback List */}
      <div className="space-y-4">
        <h2 className="text-xl font-bold text-gray-800 mb-4">Recent Reviews</h2>
        {feedbacks.length === 0 ? <p className="text-gray-500">No reviews yet.</p> : feedbacks.map(f => (
          <div key={f.id} className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 flex gap-4">
            <div className="flex-shrink-0 h-12 w-12 bg-blue-100 rounded-full flex items-center justify-center text-blue-600 font-bold text-lg">
              {f.username ? f.username.charAt(0).toUpperCase() : '?'}
            </div>
            <div>
              <div className="flex items-center gap-2 mb-1">
                <h4 className="font-bold text-gray-800">{f.username}</h4>
                <div className="text-yellow-400 text-sm">{'★'.repeat(f.rating)}</div>
                <span className="text-xs text-gray-400 bg-gray-100 px-2 py-1 rounded">Product ID: {f.productId}</span>
              </div>
              <p className="text-gray-600 leading-relaxed">{f.message}</p>
              <p className="text-xs text-gray-400 mt-2">{f.createdAt ? new Date(f.createdAt).toLocaleDateString() : 'Just now'}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
export default Feedback;