import request from '@/utils/request'

// 查询物流信息列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

export function refreshInfo(query) {
  return request({
    url: '/system/info/refresh',
    method: 'get',
    params: query
  })
}

export function refreshOneInfo(id) {
  return request({
    url: '/system/info/refreshById/' + id,
    method: 'get'
  })
}

// 查询物流信息详细
export function getInfo(id) {
  return request({
    url: '/system/info/' + id,
    method: 'get'
  })
}

// 新增物流信息
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 修改物流信息
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除物流信息
export function delInfo(id) {
  return request({
    url: '/system/info/' + id,
    method: 'delete'
  })
}
