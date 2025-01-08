import React from 'react'
import { CChartLine } from '@coreui/react-chartjs'

const MainChart = ({ chartData }) => {
  return (
    <CChartLine
      style={{ height: '300px', marginTop: '40px' }}
      data={chartData}
      options={{
        maintainAspectRatio: false,
        scales: {
          x: {
            grid: {
              drawOnChartArea: false,
            },
          },
          y: {
            beginAtZero: true,
            ticks: {
              callback: (value) => `${value.toLocaleString()}đ`,
            },
          },
        },
      }}
    />
  )
}

export default MainChart