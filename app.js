// app.js


App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
 
  },
  globalData: {
    userInfo:{nickName:'123'} 
  },
  
// 全局引入方法，简便引入第三方包
//require: ($url)=> require($url)

})
