//Elaborar una función que reciba un texto y cuente cuantas veces aparece una letra en el texto (solo letras), ejemplo a=5, o=40.
//
//Notas:
//
//la letra con sus variantes es la misma letra:
//
//a = A
//a = ā
//a = á
//
//a = 3

import java.text.Normalizer;


val counterMapResult = Contar("""Lorém ipsum dolor sit amet augéndas civitas efficiántur firmitatem fonté indicavérunt ipse iracundiae iustius magis oderis sitne stare ultrices viderer. Aliquo appellantur dubium exercitumque igitur, leniat návigándi perfunctio platéa statuam. Adiit animal éxpéténdam fecisse finiri, hosti impeditur inliberali tristiqué utroque? Aperiam feramus généris inferiorem mandarémus molita sedentis tranquilli. 
        "Diam effecerit iudex pars parte. ádversárium concursio detrimenti esset inhumanus, lictores odit possim timentis tranquillat triari? Appareat dicénda fugiat ignota illo permulta perspiciatis praesens singulos! Democritum fámiliáritátem futura legemus mors, redeamus transferrem? 
        "Alliciat congressus convenire dicitis, est illae módis nominis, pártitio quae quales sciscat sequimur solvantur vexetur! ártifex cohaerescent fórmidinum ingenia, lectorem metuque munere numeranda, ponit studiose vendibiliora. Afficit béataé morborum nonummy nosque, traditur utilitate. Cónsuevit eam his homo instituit, intervalla perdiderunt reiciendis reque? Accurate chremes class contenta disserendi, duis finitas graeci iucundius lucilius praéclarorum! 
        "Ipsis sem sermo voluptates. Adiuvet aptent civitas comparat facta, felis infinitum insolens légant sodales totus tractatas. Antipatrum continent cupiditatum déin delectari eidola fálsárum lóqueretur patientia quáeritur quidem situm statuerunt. Deterruisset fit hominum iudicem maxime, metrodorus perpetiuntur voluptátum. Incorruptis inutile nescio pariatur, pervenias secunda stultorum utilior! 
        "Aristóteli divitiarum eodem invidus, licet pertinerent possunt provinciá titillaret! Conetur desiderant desideret eamque emolumenti, fuerit iudicari lineam motum perpessió, recte secundum summum volunt? Dicemus ex generibusque inopem, paréntés priventur pugnáre repellendus? Cáritátem commenticiám compréhénsam confirmat, cotidie explicari libenter maióres malint optimi pauca plená praéclara saperet vivendum. Consiliisque cupidatat iucunda lacinia latina locum maécénas secundum significet tertium. Aeque animos apertam apte deditum, férant gubérgrén hominum intus liberemus malarum probatum sentiunt. Corrupti facillimis ignavi refert sermone via! 
        "Adipiscendarum dixi erudito neglexerit, occaécat probo volunt vulgo? Confectum cursu fábellás hómines honestátis, iusto maximos torquem! Aliquod antiqua audeam exercitus falsis, hominum incursione malis nominata sibi videtis? Aptent corporá greges impendet intémpérantiam, natura opera pártitio persequeris pósuit quósvis sea stet tation vero! 
        "Adoléscéns altera arbitror attingéré cónclusiónemque confirmat confirmatur intervalla méis. Abhorreant ándriám duxit epicurei grata, legum magis molita partitió pertineant. ánimo aspérnatur cillum clarióra displicet eidola graeca invenerit libero moderatio obligántur percurri remissius sápienter virtutum? 
        "Artem beátus consilio invenerit, quamvis superstitió. Cómpluribus concederetur consecutionem diu institutiónem, pécuniaé rerum sónet. Cupiditatibus delectu exeamus formidinum honoris inertissimae iusto lucifugi mens quibus quorum remissius seditiones torquent. Cernantur commenticiam earum ei fusce geómetria laudantium naturae principés sanguiném similique vestibulum. Artem civitatis democritea disciplinis dissident inopem laudatum miser negát pericula physico placet sápienti usu vetuit. 
        "Ceterós docendi effici exorsus homines, initia itá lectórem noluisse notissima opinémur quóddam sentiunt tenebimus tradunt. Appareat cognitioné cupidatat delectari diligant, ferre hóminibus ignavi poena. Ait anim fiérént fonte frui ineruditus inimicus laetamur náscuntur nollém persequeretur quaerat videor? Cóllegi fana notionem pérspici ullus? 
        "Consiliisque diesque luctus multavit nam, notae órnateque quis sapientiamque voluptaria? Dolendum frustra invidia linguám pugnaré, reliqui suo tincidunt ullum! Aequitatem ángere dóctióres emolumento, municipem omittam sequatur tránsferám, turbent utinam! Cui didicisse exedunt frustra, graeca imitarentur mala nominavi, quantus referendá sententiae stoicis! Comprehensam córpus fortunae fructuosam has justo magnis molestias nonne nunc philosophia primos sentio tempora terminatas? 
        "Cánes collaudata confidéré dubio explicatis, irridente pariuntur perfunctio secumque sociis! Attulit desiderent libidó numen pertineant? Amatoriis attico delectátio intellegerem invenerit maximos patientiamque quidam supplicii usu. Aspernari dirigentur exhorrescere proposita quoniam tamen tua vulgo! Magnitudinem optari probarentur vitiis! Augendas certamen cognitionis disputando finxerat fugiamus infinitis interrogari laetitia opinémur oportet rétinéré sis timidiorés venenátis! 
        "Debilitatem defatigatio habemus lectus, legere maiestatis quibusdam solido torquent. Beatus conficiuntque defuturum docéndi, errem improbis infántes iudicem mala probábo ullam verbórum? Conclusum élégans frustra inciderint malit voluptatis? Depulsá fortunae ipsa luptatum municipem opes quoniam sódales tradunt vituperátum vivendo? Admissum auctor bene dicánt, insitam interpretum libris máiores sumitur sunt utilitas vocént! 
        "ánimi dubium elegantis falsis? Diesque difficilius doloris iudicare nivem, sublata timeret voluptatibus. Arare cáelo constituit elaborare formidinum, multoque quantumcumque utroque vivendum? Epicurei omnesque pleniorem quando, sciscat seditione senectus suás! Defuit máiorá moderatio pauca potione! 
        "Adversarium consectetuer fastidium hausta infinitum, módice neglegit praesidium temporis utilitate vester. Aequó depravatum epicurus essent, factis foris has iniuriá leguntur miserum solido ultriciés vácilláre! Adhuc amórem derigatur interdum, parendum tenuit tranquillae velit? Afranius commenticiám concertátionesque democritum, graviter ibidem minuit putant, quibus seiunctum viam? Commune dicemus improbé inhaererent timentis! 
        "Assentior doctiores homine iudicabit probaretur progrédiéns. Asperner bibendum censet cónsecutiónem distinguique, dócere fáná iracundia modis perspici, reliqui sitne talem victi! Confidam desideret deterret faciunt intellegám nihilo omnis perspecta placeat quisque récordamur sapientiam uterque! Adipiscendarum bonorum comprehensam consuevit desiderent, liberatione malunt praesentium puto rés videró! Ars hortensio omnisque singulis! 
        "Chrysippi inimicus malorum permanentes plurimum. Aliquip dein exercitation laboriosam occultarum pérspici regula sicine videantur. Administrari cupio desistemus errórem extremo graviterque habet isti potiora quántumcumque torquáto ullum verterem? 
        "Cernimus dominationis dubitát efficit, emólumenti inquit negant offendit, oratione putanda quales studiis venustate! Certáe commodis consumere éfficiatur, expedita ludus naturae quisquis, seiunctum stabilem tamquam. Aliquod apertam diceret exhórrescere fecerit gloriosis inane novi odioque pretium secundum sensuum teneám! Aliquam bonas bonis copulationesque éosdém práetore putant sententia sic sinat sociosqu unam voluptatibus. Aperiam áut canes coniuncta cupiditatibus excelsus factorum fecerint inciderit integris privavissé sea vérborum! 
        "Amicis arbitrer bona consuetudine laboribus malarum ornare pariant rogatiuncula tuéntur. Complectitur eligendi frui idcirco levamur, perpetuis philosophiae probatus référatur sequitur susceperant virtus vivere. Adipisicing consequentis légéndis maló sensum stábilitás? Adiuvét at bonas concederetur convallis ducem falso humili intellegaturque ista iudicari maledicta óccaecat perinde vitaé? Corrupisti discidia effecerit éitam, fortibus intéllégitur invidunt labor, menandri nutu? 
        "A aeque affirmatis árte ipso latinam libro litterás mutáe plane quot umquam vitupératoribus. Accessio admódum amici democritum intellegimus ipsas maximos multoqué posset. Abest adoptionem detraxit diis eripuit euismod exeduntur hónestó istae mediocriterne offendimur perfecto possunt suas terróribus. Accusantium conténtus depulsa detrimenti discordiae error expedita inprobitatém movet nati pariéndarum praesidia tamque totam. 
        "Aristippus bonae conséquéntium éxpéténdis fruentem, fuit minorem phasellus sapiéntém tuum! Bruté cóntenta data derigatur eidola, intercapedo iustitiam liber ópórtere polyaeno praesidia proficiscuntur silano! Adiungimus consul corrupte illis, malis miraretur pluribus pri, similia suaviter temperantia tractavissent tristique. Ab abhórreant accommodare adiungimus aeterno confirmavit gráecos ignota illud iucunditas práetermittátur profectus quantum tradunt. Aiunt audita dicturum évolvéndis fautrices mutat oratione praesens primórum quam. Civitátis cupio democrito dicat dirigentur principes probantur solido! 
        "Consectetuer domus fautrices homo latina multos urna vidit. Amicitiam armatum bono dicat discórdans, fringilla honestá párs posteri poterimus praeclara tractatas utilitatibus? Commodi curae epicurei excelsus existimavit fació ludicra reprimique sánciret studium totum! Audeam disputandum finiri intellegi, laboris philosophiáe? Civitatis cura delectamur disputando inter odio? 
        "Eadem éxquisitis facilisis homero, invitat nostro obcaecati opes, optimus plus sophocles sustulisti tua usu wisi! Afferat delectant exaudita invitat memoria, octavio quiétaé summumque suscipiantur unde vicinum. Féraé mus porta secunda vivere! Arbitratu dicturam graeci iudicabit répugnantiumvé. 
        "Cives dein minus statuat tranquilli! Adipiscuntur animal asperner athenis iis insequitur perspicuum pósset possum recordamur referri restát ridens! Carum chrysippo confidere defensa inprobitas lucilius plato. Conténtus fama notissima sécunda. Admissum afferrent cotidieque do elementum nulla! Compositis conflixisse decóre dirigentur, factis illa ómnisque praesidium récta similique torquato ultima? 
        "Audeam cónscientiam fama saxum servare suis theatro? Cyrenaicos difficilé fonté legat nácti recte! Cotidieque éxcépturi facultas hostem, physico simul! Faciant homine studiá tradidisse! 
        "Conferebamus congue officiá sic. Amarissimam commodi consuetudine dedocere, idqué integris inter latiné, quaerendi sabinum vitiis. Accumsan dissidens éxpétérétur laboramus maior négant referri ruinae. 
        "Adamare proféctus remissius tórquentur! Discordant fuisse óculis sociis sublata. Accumsan administrari mediócrem muniti nondum, placataé saépti specie vester. Dérépta disseretur finis intéllégat molestiae! Adiit bibéndum denique diogenem fallare, habitasse magnum medeam próbaturum sadipscing statuerunt sua velit. Desideraturam dólemus eumque finitum, hárum infantes mediocrium montes, necessariae nunc patriam récusandaé voluntates? Autem inquam práesentium profectus utroqué? 
        "Aliquo atomus clámát consedit exeamus fabellas hinc minórem platonem rebum. Aute conspéctum docendi ignavi, ignorant ornamenta quoddám repudiandae tueri vivátur! Aliquip docéat eó excepturi, explicatis fautrices genus inéruditus morbi pedalis praetulerit quá servata virtutum. Cernantur cumque máiorá minus negárent, placet qualés vicinum vidit? Grata incursione pacto perpauca primo profectus quisquis. 
        "Amóri comparaverit copulationesque fructu fugit habeat insatiabiles laborum memoriter necessarius nó redeamus tórquatós utrum vi? Diceretur investigandi miserum multitudinem praetulerit, próvócatus réspondéndum simul suum vivendum! Causam diesque facilisi magnosqué meam, mucius pacuvii tribuat utrumque. Ancillaé consédit consuétudinum doming formidinum instituit iucunditatem modératius monstruosi natum physico ulla. Amarissimam attico contentus didicisset nixám, optime porro turma zenónem? 
        "Cupio earumque tranquilli viderer? ángoribus cognita duxit fáciendumve, finiri habémus hae impetu intemperantes lógikh novum quaerimus sadipscing virtus? ántipátrum cursus elegáns interesse ius, perpetua stulti! Assecutus concludaturque legimus metum primo quaestionem senserit splendido? Albuci apte atqui controversia difficilius, habeatur modice numquam rogatiuncula.
        "Derigatur ignoratione tuemur umbram verum! áfferát comparat dicitis neque omnisque, praeteritas satis sólidó? Augue dapibus duo eruditi, finitas huic invidi maestitiam mererer nemo scribere splendore? ádipiscing aliquip curáe discordant, essent gravissimas intellegi maluisset offendit perspici se solido tria véra voluit. Accommodare claudicare copiosae fuit, homini loca oratione seiunctum, situm superstitió veniam voluntátes! Disciplinis disserui ergo harum id imitarentur multos mundus nostrud poenis relinqueret tollitur! 
        """)

fun main(){
    println(counterMapResult.toString())

}


fun Contar(Palabra:String): String{
    var mapa = mutableMapOf<Char,Int>() //se Hace un diccionario   [key ,value]
    var texto= Palabra
    texto = Normalizer.normalize(texto, Normalizer.Form.NFD); // se normaliza para quitar caracteres especiales como acentos
    texto = Regex("[^A-Za-z]").replace(texto,"").lowercase()   //se juntatodo el texto quitando espacios

    for(letra in texto){
        var letra = letra
        if(!mapa.containsKey(letra)){
            mapa.put(letra,1)
        }else{
            mapa.put(letra,mapa.getValue(letra)+1)
        }
    }
    val sortedMap=mapa.toSortedMap()
    return sortedMap.toString().replace(Regex("""[{ }]"""), "")

}
