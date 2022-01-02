import request from '@/utils/request'

// 查询收货人信息列表
export function listReceiver(query) {
  return request({
    url: '/system/receiver/list',
    method: 'get',
    params: query
  })
}

// 查询收货人信息详细
export function getReceiver(id) {
  return request({
    url: '/system/receiver/' + id,
    method: 'get'
  })
}

// 新增收货人信息
export function addReceiver(data) {
  return request({
    url: '/system/receiver',
    method: 'post',
    data: data
  })
}

// 修改收货人信息
export function updateReceiver(data) {
  return request({
    url: '/system/receiver',
    method: 'put',
    data: data
  })
}

// 删除收货人信息
export function delReceiver(id) {
  return request({
    url: '/system/receiver/' + id,
    method: 'delete'
  })
}
