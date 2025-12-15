import { useState, useEffect, useContext } from 'react';
import api from '../api';
import { AuthContext } from '../context/AuthContext';

function Products() {
  const [products, setProducts] = useState([]);
  const [newProduct, setNewProduct] = useState({ name: '', description: '', price: '', stock: '' });
  const { user } = useContext(AuthContext);

  const loadProducts = () => {
    api.get('/products')
      .then(res => setProducts(res.data))
      .catch(err => console.error("Failed to load products", err));
  };

  useEffect(loadProducts, []);

  const handleAddProduct = async (e) => {
    e.preventDefault();
    try {
      await api.post('/products', newProduct);
      setNewProduct({ name: '', description: '', price: '', stock: '' });
      loadProducts();
      alert('Product Added! Analytics event sent.');
    } catch (err) {
      alert('Failed to add product. Ensure you are logged in.');
      console.error(err);
    }
  };

  return (
    <div className="container mx-auto px-6 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">Product Catalog</h1>

      {/* Add Product Form - Protected */}
      {user && (
        <div className="bg-white p-6 rounded-xl shadow-md mb-10 border border-gray-100">
          <h2 className="text-xl font-bold mb-4 text-gray-700">Add New Product</h2>
          <form onSubmit={handleAddProduct} className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input
              placeholder="Product Name"
              className="p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"
              value={newProduct.name}
              onChange={e => setNewProduct({...newProduct, name: e.target.value})}
              required
            />
            <input
              placeholder="Price"
              type="number"
              className="p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"
              value={newProduct.price}
              onChange={e => setNewProduct({...newProduct, price: e.target.value})}
              required
            />
            <input
              placeholder="Stock"
              type="number"
              className="p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"
              value={newProduct.stock}
              onChange={e => setNewProduct({...newProduct, stock: e.target.value})}
              required
            />
            <input
              placeholder="Description"
              className="p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none md:col-span-2"
              value={newProduct.description}
              onChange={e => setNewProduct({...newProduct, description: e.target.value})}
            />
            <button type="submit" className="bg-green-600 text-white py-3 rounded-lg hover:bg-green-700 font-semibold md:col-span-2 shadow-md transition-all">
              + Create Product
            </button>
          </form>
        </div>
      )}

      {/* Product Grid */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {products.map(p => (
          <div key={p.id} className="bg-white p-6 rounded-xl shadow-sm border border-gray-200 hover:shadow-xl transition-all flex flex-col justify-between">
            <div>
              <div className="flex justify-between items-start mb-2">
                <h3 className="text-xl font-bold text-gray-800">{p.name}</h3>
                <span className="bg-blue-100 text-blue-800 text-xs font-semibold px-2.5 py-0.5 rounded">
                  ${p.price}
                </span>
              </div>
              <p className="text-gray-600 text-sm mb-4">{p.description}</p>
            </div>
            <div className="text-xs text-gray-400 mt-2 pt-2 border-t border-gray-100">
              Stock: {p.stock} units
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
export default Products;