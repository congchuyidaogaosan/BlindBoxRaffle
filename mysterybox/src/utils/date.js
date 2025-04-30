/**
 * 格式化日期时间
 * @param {string|Date} date 日期对象或日期字符串
 * @param {string} format 格式化模板，默认为 'YYYY-MM-DD HH:mm:ss'
 * @returns {string} 格式化后的日期字符串
 */
export function formatDateTime(date, format = 'YYYY-MM-DD HH:mm:ss') {
  if (!date) return '';
  
  const d = typeof date === 'string' ? new Date(date.replace(/-/g, '/')) : new Date(date);
  
  if (isNaN(d.getTime())) return '';
  
  const o = {
    'Y+': d.getFullYear(),
    'M+': d.getMonth() + 1,
    'D+': d.getDate(),
    'H+': d.getHours(),
    'm+': d.getMinutes(),
    's+': d.getSeconds()
  };
  
  for (const k in o) {
    if (new RegExp(`(${k})`).test(format)) {
      const str = String(o[k]);
      format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str));
    }
  }
  
  return format;
}

/**
 * 左侧补零
 * @param {string} str 
 * @returns {string}
 */
function padLeftZero(str) {
  return ('00' + str).slice(String(str).length);
}

/**
 * 计算日期相对于当前的友好显示
 * @param {string|Date} date 日期对象或日期字符串
 * @returns {string} 友好的日期显示
 */
export function formatDateTimeToFriendly(date) {
  if (!date) return '';
  
  const d = typeof date === 'string' ? new Date(date.replace(/-/g, '/')) : new Date(date);
  
  if (isNaN(d.getTime())) return '';
  
  const now = new Date();
  const diff = now.getTime() - d.getTime();
  
  // 一分钟内
  if (diff < 60 * 1000) return '刚刚';
  
  // 一小时内
  if (diff < 60 * 60 * 1000) {
    const minutes = Math.floor(diff / (60 * 1000));
    return `${minutes}分钟前`;
  }
  
  // 一天内
  if (diff < 24 * 60 * 60 * 1000) {
    const hours = Math.floor(diff / (60 * 60 * 1000));
    return `${hours}小时前`;
  }
  
  // 一周内
  if (diff < 7 * 24 * 60 * 60 * 1000) {
    const days = Math.floor(diff / (24 * 60 * 60 * 1000));
    return `${days}天前`;
  }
  
  // 一年内，显示月日
  if (diff < 365 * 24 * 60 * 60 * 1000) {
    return formatDateTime(d, 'MM-DD HH:mm');
  }
  
  // 超过一年，显示年月日
  return formatDateTime(d, 'YYYY-MM-DD');
}

