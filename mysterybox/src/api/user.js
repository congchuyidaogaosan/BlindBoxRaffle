import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/auth/login',
    method: 'post',
    data
  })
}


export function getUserInfo() {
  return request({
    url: '/api/users/profile',
    method: 'get'
  })
}

export function getCounts() {
  return request({
    url: '/api/users/counts',
    method: 'get'
  })
}

export function updateProfile(data) {
  return request({
    url: '/api/users/profile',
    method: 'put',
    data
  })
}

// 获取用户列表
export function getUsers() {
  return request({
    url: '/api/user/list',
    method: 'get'
  })
}

// 逻辑删除用户
export function deleteUser(userId) {
  return request({
    url: `/api/user/delete/${userId}`,
    method: 'post'
  })
} 