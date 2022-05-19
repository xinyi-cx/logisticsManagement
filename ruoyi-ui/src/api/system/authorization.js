import request from '@/utils/request'

// 查询用户授权信息列表
export function listAuthorization(query) {
  return request({
    url: '/system/authorization/list',
    method: 'get',
    params: query
  })
}

// 查询用户授权信息详细
export function getAuthorization(userId) {
  return request({
    url: '/system/authorization/' + userId,
    method: 'get'
  })
}

// 新增用户授权信息
export function addAuthorization(data) {
  return request({
    url: '/system/authorization',
    method: 'post',
    data: data
  })
}

// 修改用户授权信息
export function updateAuthorization(data) {
  return request({
    url: '/system/authorization',
    method: 'put',
    data: data
  })
}

// 删除用户授权信息
export function delAuthorization(userId) {
  return request({
    url: '/system/authorization/' + userId,
    method: 'delete'
  })
}
