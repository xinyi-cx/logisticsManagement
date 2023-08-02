import request from '@/utils/request'

// 查询同步历史记录列表
export function listDpd(query) {
  return request({
    url: '/system/dpd/list',
    method: 'get',
    params: query
  })
}

// 查询同步历史记录详细
export function getDpd(id) {
  return request({
    url: '/system/dpd/' + id,
    method: 'get'
  })
}

// 新增同步历史记录
export function addDpd(data) {
  return request({
    url: '/system/dpd',
    method: 'post',
    data: data
  })
}

// 修改同步历史记录
export function updateDpd(data) {
  return request({
    url: '/system/dpd',
    method: 'put',
    data: data
  })
}

// 删除同步历史记录
export function delDpd(id) {
  return request({
    url: '/system/dpd/' + id,
    method: 'delete'
  })
}
