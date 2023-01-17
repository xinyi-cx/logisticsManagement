import request from '@/utils/request'

// 查询导入查询物流列表
export function listContent(query) {
  return request({
    url: '/system/content/list',
    method: 'get',
    params: query
  })
}

// 查询导入查询物流详细
export function getContent(id) {
  return request({
    url: '/system/content/' + id,
    method: 'get'
  })
}

// 新增导入查询物流
export function addContent(data) {
  return request({
    url: '/system/content',
    method: 'post',
    data: data
  })
}

// 修改导入查询物流
export function updateContent(data) {
  return request({
    url: '/system/content',
    method: 'put',
    data: data
  })
}

// 删除导入查询物流
export function delContent(id) {
  return request({
    url: '/system/content/' + id,
    method: 'delete'
  })
}

export function refreshToday(query) {
  return request({
    url: '/system/content/refreshToday',
    method: 'get',
    params: query
  })
}

export function refreshQuery(query) {
  return request({
    url: '/system/content/refreshQuery',
    method: 'get',
    params: query
  })
}
