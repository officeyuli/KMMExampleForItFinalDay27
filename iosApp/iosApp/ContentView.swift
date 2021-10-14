import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var cafeEntityViewModel = CafeEntityItemViewModel()
    
    var cafes: [CAFE]?
    var body: some View{
        NavigationView {
            CafeListContent(cafelist: cafeEntityViewModel.cafes)
            .navigationBarTitle(Text("CafeList"), displayMode: .large)
            .onAppear(perform: {
                self.cafeEntityViewModel.initData()
            })
            .onDisappear(perform: {
                self.cafeEntityViewModel.onDestroy()
            })
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct CafeListContent: View{
    var cafelist: [CAFE]?
    var body : some View {
        ZStack{
            if let cafes = cafelist {
                List(cafes, id: \.id) { cafe in
                    CafeItemView(cafeItem: cafe)
                }
            }else{
                Text("Empty")
            }
        }
    }
}

struct CafeItemView : View {
    var cafeItem: CAFE

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(cafeItem.name!).font(.headline)
                Text(cafeItem.id).font(.subheadline)
            }
        }
    }
}
