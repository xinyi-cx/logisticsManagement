import request from '@/utils/request'

// 查询马帮订单信息列表
export function listDto(query) {
  return request({
    url: '/system/dto/list',
    method: 'get',
    params: query
  })
}

// 查询马帮订单信息详细
export function getDto(code) {
  return request({
    url: '/system/dto/' + code,
    method: 'get'
  })
}

// 新增马帮订单信息
export function addDto(data) {
  return request({
    url: '/system/dto',
    method: 'post',
    data: data
  })
}

// 修改马帮订单信息
export function updateDto(data) {
  return request({
    url: '/system/dto',
    method: 'put',
    data: data
  })
}

// 删除马帮订单信息
export function delDto(code) {
  return request({
    url: '/system/dto/' + code,
    method: 'delete'
  })
}
