import { useEffect, useState } from 'react';
import api from '../api';

function Dashboard() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    api.get('/analytics/stats')
      .then(res => setStats(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="container mx-auto px-6 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-800">Dashboard Overview</h1>
        <p className="text-gray-500">Real-time metrics from your system.</p>
      </div>

      {stats ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {Object.entries(stats.eventCounts).map(([event, count]) => (
            <div key={event} className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 hover:shadow-md transition-shadow">
              <div className="flex items-center justify-between mb-4">
                <h3 className="text-gray-500 text-sm font-medium uppercase tracking-wider">{event.replace(/_/g, ' ')}</h3>
                <span className="p-2 bg-blue-50 text-blue-600 rounded-lg">
                  📊
                </span>
              </div>
              <p className="text-4xl font-bold text-gray-800">{count}</p>
            </div>
          ))}
        </div>
      ) : (
        <div className="flex justify-center items-center h-64">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
        </div>
      )}
    </div>
  );
}
export default Dashboard;