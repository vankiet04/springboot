import React, { useState, useEffect } from 'react'
import {
  CButton,
  CButtonGroup,  
  CCard,
  CCardBody,
  CCol,
  CRow,
  CFormInput
} from '@coreui/react'
import MainChart from './MainChart'

const Dashboard = () => {
  const [orderData, setOrderData] = useState([])
  const [dateRange, setDateRange] = useState({
    startDay: 1,
    endDay: 31,
    month: new Date().getMonth() + 1,
    year: new Date().getFullYear()
  })

  useEffect(() => {
    fetchOrders()
  }, [])

  const fetchOrders = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/orders/getall')
      const data = await response.json()
      setOrderData(data)
    } catch (error) {
      console.error('Error fetching orders:', error)
    }
  }

  // Process order data for chart
  const processOrderData = () => {
    const dailyData = {}
    
    // Initialize all days with 0
    for(let i = dateRange.startDay; i <= dateRange.endDay; i++) {
      dailyData[i] = 0
    }

    // Sum up orders by day
    orderData.forEach(order => {
      const orderDate = new Date(order.orderDate)
      const day = orderDate.getDate()
      if (orderDate.getMonth() + 1 === dateRange.month && 
          orderDate.getFullYear() === dateRange.year &&
          day >= dateRange.startDay && 
          day <= dateRange.endDay) {
        dailyData[day] = (dailyData[day] || 0) + order.totalPrice
      }
    })

    return {
      labels: Object.keys(dailyData),
      datasets: [{
        label: 'Daily Sales',
        data: Object.values(dailyData)
      }]
    }
  }

  return (
    <>
      <CCard className="mb-4">
        <CCardBody>
          <CRow>
            <CCol sm={5}>
              <h4 id="traffic" className="card-title mb-0">
                Sales Statistics
              </h4>
            </CCol>
            <CCol sm={7} className="d-none d-md-block">
              <CRow>
                <CCol sm={3}>
                  <CFormInput
                    type="number"
                    label="Start Day"
                    value={dateRange.startDay}
                    onChange={(e) => setDateRange({
                      ...dateRange,
                      startDay: parseInt(e.target.value)
                    })}
                    min={1}
                    max={31}
                  />
                </CCol>
                <CCol sm={3}>
                  <CFormInput
                    type="number" 
                    label="End Day"
                    value={dateRange.endDay}
                    onChange={(e) => setDateRange({
                      ...dateRange,
                      endDay: parseInt(e.target.value)
                    })}
                    min={1}
                    max={31}
                  />
                </CCol>
                <CCol sm={3}>
                  <CFormInput
                    type="number"
                    label="Month"
                    value={dateRange.month}
                    onChange={(e) => setDateRange({
                      ...dateRange,
                      month: parseInt(e.target.value)
                    })}
                    min={1}
                    max={12}
                  />
                </CCol>
                <CCol sm={3}>
                  <CFormInput
                    type="number"
                    label="Year"
                    value={dateRange.year}
                    onChange={(e) => setDateRange({
                      ...dateRange, 
                      year: parseInt(e.target.value)
                    })}
                  />
                </CCol>
              </CRow>
              <CButton 
                color="primary"
                onClick={fetchOrders}
                className="mt-3"
              >
                Thống kê
              </CButton>
            </CCol>
          </CRow>
          <MainChart chartData={processOrderData()} />
        </CCardBody>
      </CCard>
    </>
  )
}

export default Dashboard