import request from '@/utils/request'

// 查询转寄面单原面单关联关系列表
export function listRedirect(query) {
  return request({
    url: '/system/redirect/list',
    method: 'get',
    params: query
  })
}

// 查询转寄面单原面单关联关系详细
export function getRedirect(id) {
  return request({
    url: '/system/redirect/' + id,
    method: 'get'
  })
}

// 新增转寄面单原面单关联关系
export function addRedirect(data) {
  return request({
    url: '/system/redirect',
    method: 'post',
    data: data
  })
}

// 修改转寄面单原面单关联关系
export function updateRedirect(data) {
  return request({
    url: '/system/redirect',
    method: 'put',
    data: data
  })
}

// 删除转寄面单原面单关联关系
export function delRedirect(id) {
  return request({
    url: '/system/redirect/' + id,
    method: 'delete'
  })
}
