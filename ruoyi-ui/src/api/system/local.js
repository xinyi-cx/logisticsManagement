import request from '@/utils/request'

// 查询本地关联关系列表
export function listLocal(query) {
  return request({
    url: '/system/local/list',
    method: 'get',
    params: query
  })
}

// 查询本地关联关系详细
export function getLocal(id) {
  return request({
    url: '/system/local/' + id,
    method: 'get'
  })
}

// 新增本地关联关系
export function addLocal(data) {
  return request({
    url: '/system/local',
    method: 'post',
    data: data
  })
}

// 修改本地关联关系
export function updateLocal(data) {
  return request({
    url: '/system/local',
    method: 'put',
    data: data
  })
}

// 删除本地关联关系
export function delLocal(id) {
  return request({
    url: '/system/local/' + id,
    method: 'delete'
  })
}
