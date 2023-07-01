import request from '@/utils/request'

// 查询user deliver infomation table列表
export function listUserdeliverinfo(query) {
  return request({
    url: '/system/userdeliverinfo/list',
    method: 'get',
    params: query
  })
}

// 查询user deliver infomation table详细
export function getUserdeliverinfo(id) {
  return request({
    url: '/system/userdeliverinfo/' + id,
    method: 'get'
  })
}

// 新增user deliver infomation table
export function addUserdeliverinfo(data) {
  return request({
    url: '/system/userdeliverinfo',
    method: 'post',
    data: data
  })
}

// 修改user deliver infomation table
export function updateUserdeliverinfo(data) {
  return request({
    url: '/system/userdeliverinfo',
    method: 'put',
    data: data
  })
}

// 删除user deliver infomation table
export function delUserdeliverinfo(id) {
  return request({
    url: '/system/userdeliverinfo/' + id,
    method: 'delete'
  })
}
