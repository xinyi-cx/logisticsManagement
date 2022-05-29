import request from '@/utils/request'

// 查询马帮信息列表
export function listMsg(query) {
  return request({
    url: '/system/msg/list',
    method: 'get',
    params: query
  })
}

// 查询马帮信息详细
export function getMsg(code) {
  return request({
    url: '/system/msg/' + code,
    method: 'get'
  })
}

// 新增马帮信息
export function addMsg(data) {
  return request({
    url: '/system/msg',
    method: 'post',
    data: data
  })
}

// 修改马帮信息
export function updateMsg(data) {
  return request({
    url: '/system/msg',
    method: 'put',
    data: data
  })
}

// 删除马帮信息
export function delMsg(code) {
  return request({
    url: '/system/msg/' + code,
    method: 'delete'
  })
}
