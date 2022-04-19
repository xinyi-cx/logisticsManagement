import request from '@/utils/request'

// 查询物流单号和L关联列表
export function listRel(query) {
  return request({
    url: '/system/rel/list',
    method: 'get',
    params: query
  })
}

// 查询物流单号和L关联详细
export function getRel(waybill) {
  return request({
    url: '/system/rel/' + waybill,
    method: 'get'
  })
}

// 新增物流单号和L关联
export function addRel(data) {
  return request({
    url: '/system/rel',
    method: 'post',
    data: data
  })
}

// 修改物流单号和L关联
export function updateRel(data) {
  return request({
    url: '/system/rel',
    method: 'put',
    data: data
  })
}

// 删除物流单号和L关联
export function delRel(waybill) {
  return request({
    url: '/system/rel/' + waybill,
    method: 'delete'
  })
}
