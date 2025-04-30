import request from '@/utils/request'

// 获取评论列表
export function getCommentList(params) {
  return request({
    url: '/api/Review/list',
    method: 'get',
    params
  })
}

// 提交评论
export function submitComment(data) {
  return request({
    url: '/api/Review',
    method: 'post',
    data
  })
}

// 删除评论
export function deleteComment(id) {
  return request({
    url: `/api/Review/${id}`,
    method: 'delete'
  })
}

// 备用删除评论方法（使用POST请求）
export function deleteCommentPost(id) {
  return request({
    url: `/api/Review/delete/${id}`,
    method: 'post',
  })
} 