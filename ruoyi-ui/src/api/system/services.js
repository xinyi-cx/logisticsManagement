import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listServices(query) {
  return request({
    url: '/system/services/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getServices(id) {
  return request({
    url: '/system/services/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addServices(data) {
  return request({
    url: '/system/services',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateServices(data) {
  return request({
    url: '/system/services',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delServices(id) {
  return request({
    url: '/system/services/' + id,
    method: 'delete'
  })
}
