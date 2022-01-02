import request from '@/utils/request'

// 查询发件人信息列表
export function listSender(query) {
  return request({
    url: '/system/sender/list',
    method: 'get',
    params: query
  })
}

// 查询发件人信息详细
export function getSender(id) {
  return request({
    url: '/system/sender/' + id,
    method: 'get'
  })
}

// 新增发件人信息
export function addSender(data) {
  return request({
    url: '/system/sender',
    method: 'post',
    data: data
  })
}

// 修改发件人信息
export function updateSender(data) {
  return request({
    url: '/system/sender',
    method: 'put',
    data: data
  })
}

// 删除发件人信息
export function delSender(id) {
  return request({
    url: '/system/sender/' + id,
    method: 'delete'
  })
}
