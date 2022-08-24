import request from '@/utils/request'

// 查询面单dpd对应关系列表
export function listMapping(query) {
  return request({
    url: '/system/mapping/list',
    method: 'get',
    params: query
  })
}

// 查询面单dpd对应关系详细
export function getMapping(id) {
  return request({
    url: '/system/mapping/' + id,
    method: 'get'
  })
}

// 新增面单dpd对应关系
export function addMapping(data) {
  return request({
    url: '/system/mapping',
    method: 'post',
    data: data
  })
}

// 修改面单dpd对应关系
export function updateMapping(data) {
  return request({
    url: '/system/mapping',
    method: 'put',
    data: data
  })
}

// 删除面单dpd对应关系
export function delMapping(id) {
  return request({
    url: '/system/mapping/' + id,
    method: 'delete'
  })
}
