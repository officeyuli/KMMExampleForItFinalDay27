//
//  CafeResponseItemViewModel.swift
//  iosApp
//
//  Created by yuli on 2021/10/11.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
class CafeResponseItemViewModel: ObservableObject{
    @Published var cafeResponseItemList = [CafeResponseItem]()

    private let repository: DataRepository
        
    init(repository: DataRepository) {
        self.repository = repository
    }
    
    func fetch() {
        repository.fetchCafesFromNetwork(cityName:"taipei"){ result , error in
            if let result = result{
                self.cafeResponseItemList = result
            }
        }
    }
}
class CafeEntityItemViewModel:ObservableObject{
    private var viewModel: IOSNativeViewModel? = nil
    @Published
    var cafes:[CAFE]? = nil
    func initData(){
        viewModel = IOSNativeViewModel{ [weak self] data in
            self?.cafes = data

        }
    }
    func onDestroy(){
        viewModel?.onDestroy()
        viewModel = nil
    }
    
}
