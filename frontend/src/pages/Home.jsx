import { Link } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

function Home() {
  const { user } = useContext(AuthContext);

  return (
    <div className="bg-white">
      {/* --- HERO SECTION --- */}
      <div className="relative isolate px-6 pt-14 lg:px-8">
        {/* Background Gradient Blob (Top) */}
        <div className="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80">
          <div className="relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%-30rem)] sm:w-[72.1875rem]" />
        </div>
        
        <div className="mx-auto max-w-2xl py-32 sm:py-48 lg:py-56 text-center">
          <div className="hidden sm:mb-8 sm:flex sm:justify-center">
            <div className="relative rounded-full px-3 py-1 text-sm leading-6 text-gray-600 ring-1 ring-gray-900/10 hover:ring-gray-900/20">
              New features added. <Link to="/products" className="font-semibold text-indigo-600"><span className="absolute inset-0" />Check out our products <span aria-hidden="true">&rarr;</span></Link>
            </div>
          </div>
          <h1 className="text-4xl font-bold tracking-tight text-gray-900 sm:text-6xl">
            Customer Feedback, <span className="text-indigo-600">Reimagined.</span>
          </h1>
          <p className="mt-6 text-lg leading-8 text-gray-600">
            Empower your business with real-time insights. Collect, analyze, and act on customer feedback seamlessly with our modern microservices platform.
          </p>
          <div className="mt-10 flex items-center justify-center gap-x-6">
            
            {/* DYNAMIC BUTTON LOGIC */}
            <Link 
              to={user ? "/dashboard" : "/login"} 
              className="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
              {user ? "Go to Dashboard" : "Get Started"}
            </Link>

            <Link to="/products" className="text-sm font-semibold leading-6 text-gray-900">
              Browse Products <span aria-hidden="true">→</span>
            </Link>
          </div>
        </div>
        
        {/* Background Gradient Blob (Bottom) */}
        <div className="absolute inset-x-0 top-[calc(100%-13rem)] -z-10 transform-gpu overflow-hidden blur-3xl sm:top-[calc(100%-30rem)]">
          <div className="relative left-[calc(50%+3rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-30 sm:left-[calc(50%+36rem)] sm:w-[72.1875rem]" />
        </div>
      </div>

      {/* --- FEATURES SECTION --- */}
      <div className="py-24 sm:py-32 bg-gray-50">
        <div className="mx-auto max-w-7xl px-6 lg:px-8">
          <div className="mx-auto max-w-2xl text-center">
            <h2 className="text-base font-semibold leading-7 text-indigo-600">Why Choose Us?</h2>
            <p className="mt-2 text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Everything you need to scale</p>
          </div>
          <div className="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-4xl">
            <dl className="grid max-w-xl grid-cols-1 gap-x-8 gap-y-10 lg:max-w-none lg:grid-cols-2 lg:gap-y-16">
               
               {/* Feature 1 */}
               <div className="relative pl-16">
                  <dt className="text-base font-semibold leading-7 text-gray-900">
                    <div className="absolute left-0 top-0 flex h-10 w-10 items-center justify-center rounded-lg bg-indigo-600 text-white font-bold">
                       📊
                    </div>
                    Real-time Analytics
                  </dt>
                  <dd className="mt-2 text-base leading-7 text-gray-600">Track user engagement and product performance instantly via our live dashboard events.</dd>
               </div>

               {/* Feature 2 */}
               <div className="relative pl-16">
                  <dt className="text-base font-semibold leading-7 text-gray-900">
                    <div className="absolute left-0 top-0 flex h-10 w-10 items-center justify-center rounded-lg bg-indigo-600 text-white font-bold">
                       🛡️
                    </div>
                    Secure Architecture
                  </dt>
                  <dd className="mt-2 text-base leading-7 text-gray-600">Built with Spring Boot Microservices and JWT authentication for enterprise-grade security.</dd>
               </div>

                {/* Feature 3 */}
               <div className="relative pl-16">
                  <dt className="text-base font-semibold leading-7 text-gray-900">
                    <div className="absolute left-0 top-0 flex h-10 w-10 items-center justify-center rounded-lg bg-indigo-600 text-white font-bold">
                       ⚡
                    </div>
                    High Performance
                  </dt>
                  <dd className="mt-2 text-base leading-7 text-gray-600">Optimized Docker containers, Eureka discovery, and PostgreSQL databases ensure low latency.</dd>
               </div>

               {/* Feature 4 */}
               <div className="relative pl-16">
                  <dt className="text-base font-semibold leading-7 text-gray-900">
                    <div className="absolute left-0 top-0 flex h-10 w-10 items-center justify-center rounded-lg bg-indigo-600 text-white font-bold">
                       🛒
                    </div>
                    Product Management
                  </dt>
                  <dd className="mt-2 text-base leading-7 text-gray-600">Easily manage your product catalog and gather specific customer feedback per item.</dd>
               </div>

            </dl>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;