package com.bearslovebeer.tifitiappp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.adapter.NewsAdapter
import com.bearslovebeer.tifitiappp.models.News
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    // Partiendo de la clase data "News" vamos a instanciar (crear un objeto de esa clase, pero con atributos en vez de variables) una lista de noticias.
    val newsObject = listOf<News>(
        News(
            "LE DAMOS VIDA AL FESTIVAL",
            "Tras bambalinas de las nuevas arenas de Designios: Festival de las Bestias.",
            "08/01/21",
            "https://loldominicana.net/wp-content/uploads/2021/01/FoB_MakingOf_ArticleHeader.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/dev/bringing-the-festival-to-life/"
        ),
        News(
            "TEAMFIGHT TACTICS: NOTAS DE LA VERSIÓN 11.1",
            "¡Un balance general antes del comienzo del Festival!",
            "05/01/21",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt51d7a5c9cd6388ab/5ff3a8c7396e65084a9e8636/TFT_Patch_11_1_Notes_Banner.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-patch-11-1-notes/"
        ),
        News(
            "PASE DE TEAMFIGHT TACTICS: DESTINOS II",
            "El Festival de bestias trae consigo minileyendas, arenas, paquetes y un nuevo pase..",
            "05/01/21",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt62f37678b6b6f71d/5fd943c715555a337d776106/FoB_BattlePass_Twitter.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/fates-ii-pass-and-more/"
        ),
        News(
            "RESUMEN DE LA EXPERIENCIA DE JUEGO DE DESTINOS II",
            "El Festival de bestias trae consigo una hueste de nuevos campeones y atributos.",
            "04/01/21",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt77ce89cfc6b9b0bc/5fd7ff20a84f233eaf6118ef/FoB_CompMechanic_Article.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-fates-ii-gameplay-overview/"
        ),
        News(
            "FESTIVAL DE BESTIAS",
            "No es un sueño, es real. ¡Ha llegado la hora del anuncio de mitad del set de Destinos!",
            "14/12/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt6024d6a7d9077a2d/5fd2f30fe53f3355047dcae7/FoB_Reveal_Article_Banner.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/festival-of-beasts/"
        ),
        News(
            "NOTAS DE LA VERSIÓN 10.25 DE TEAMFIGHT TACTICS",
            "¡Mejoras para atributos en apuros en la última actualización de TFT!",
            "08/12/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/bltd57a9c0457b61032/5fcac2f372a3526f28dc1cd9/TFT_Patch_10_25_Notes_Banner.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-patch-10-25-notes/"
        ),
        News(
            "NOTAS DE LA VERSIÓN 10.24 DE TEAMFIGHT TACTICS",
            "¡Los cambios a los sistemas revolucionan el metajuego en la última actualización de TFT!",
            "23/11/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt29d83b5e65a5b179/5fb84890fd99385ff6007ad3/Lulu_00_Base.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-patch-10-24-notes/"
        ),
        News(
            "NOTAS DE LA VERSIÓN 10.23 DE TEAMFIGHT TACTICS",
            "¡Llegan a TFT las minileyendas de K/DA y una actualización para Xin Zhao!",
            "10/11/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt2a202fdfe0c06465/5fa4cc3e65bdd35303dffdf9/TFT_Patch_10_23_Notes_Banner.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-patch-10-23-notes/"
        ),
        News(
            "NOTAS DE LA VERSIÓN 10.22 DE TEAMFIGHT TACTICS",
            "¡Custodios y sectarios más fuertes en la última actualización de TFT!",
            "27/10/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blt6a098ec95863912c/5f9a066d89353a72dcaebaca/Copy-of-Majestic_Empress_Morgana_optimized.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/game-updates/teamfight-tactics-patch-10-22-notes/"
        ),
        News(
            "HABLEMOS DE LOL (23/10)",
            "Cómo evaluamos la personalización, recta final de la pretemporada en la beta, cosas de TFT que no lanzamos.",
            "23/10/20",
            "https://images.contentstack.io/v3/assets/blt731acb42bb3d1659/blta3fa3c8f48b2c4c4/5e2feac1f9ca8a42666f164d/Ziggs.jpg",
            "https://teamfighttactics.leagueoflegends.com/es-es/news/dev/quick-lol-thoughts-oct-23/"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Tifiti Manager - News";
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecycler()
    }

    override fun onStart() {
        super.onStart()
    }

    fun initRecycler(){
        news_recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NewsAdapter(newsObject)
        news_recyclerView.adapter = adapter
    }
}