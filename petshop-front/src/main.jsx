import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Route, Routes } from 'react-router'
import Dashboard from './pages/Dashboard.jsx'
import Product from './pages/Product.jsx'
import Login from './pages/Login.jsx'
import Profile from './pages/Profile.jsx'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <StrictMode>
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route path='/product/:id' element={<Product />} />
        <Route path='/login' element={<Login />} />
        <Route path='/profile' element={<Profile />} />
      </Routes>
    </StrictMode>
  </BrowserRouter>,
)
