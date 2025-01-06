const cloud = require('wx-server-sdk')
cloud.init()
const db = cloud.database()

exports.main = async (event, context) => {
  const { limit = 3 } = event
  
  try {
    const { data } = await db.collection('box_styles')
      .orderBy('sales', 'desc')
      .limit(limit)
      .get()
      
    return {
      code: 0,
      data,
      message: 'success'
    }
  } catch (error) {
    return {
      code: -1,
      data: null,
      message: error.message
    }
  }
} 