import request from '@/utils/request'

// 查询面单返回值列表
export function listResponse(query) {
  return request({
    url: '/system/response/list',
    method: 'get',
    params: query
  })
}

// 查询面单返回值详细
export function getResponse(id) {
  return request({
    url: '/system/response/' + id,
    method: 'get'
  })
}

// 新增面单返回值
export function addResponse(data) {
  return request({
    url: '/system/response',
    method: 'post',
    data: data
  })
}

// 修改面单返回值
export function updateResponse(data) {
  return request({
    url: '/system/response',
    method: 'put',
    data: data
  })
}

// 删除面单返回值
export function delResponse(id) {
  return request({
    url: '/system/response/' + id,
    method: 'delete'
  })
}
