import scala.collection.mutable.ListBuffer

object ListExercises {

  object SetA {
    // Exercise 1. print all names in list
    def printAllNames = {
      val names = IndexedSeq("Ben", "Jafar", "Matt", "Priya", "Brian")
      for (i <- 0 until names.length) {
        println(names(i))
      }
    }

    //printAllNames

    // Exercise 2. use foreach to print all the names in ... whatever
    def useForEach = {
      val names = Seq("Ben", "Jafar", "Matt", "Priya", "Brian")
      names.foreach(name => println(name))
    }

    //useForEach

    case class Bookmark(id: Long, time: Long)

    case class Video(id: Long, title: String, boxart: String, url: String, rating: Double, bookmark: Seq[Bookmark])

    val newReleases = Seq(
      Video(70111470L,
        "Die Hard",
        "http://cdn-0.nflximg.com/images/2891/DieHard.jpg",
        "http://api.netflix.com/catalog/titles/movies/70111470",
        4.0,
        Seq()),
      Video(654356453L,
        "Bad Boys",
        "http://cdn-0.nflximg.com/images/2891/BadBoys.jpg",
        "http://api.netflix.com/catalog/titles/movies/70111470",
        5.0,
        Seq(Bookmark(432534, 65876586L))),
      Video(65432445L,
        "The Chamber",
        "http://cdn-0.nflximg.com/images/2891/TheChamber.jpg",
        "http://api.netflix.com/catalog/titles/movies/70111470",
        4.0,
        Seq()),
      Video(675465L,
        "Fracture",
        "http://cdn-0.nflximg.com/images/2891/Fracture.jpg",
        "http://api.netflix.com/catalog/titles/movies/70111470",
        5.0,
        Seq(Bookmark(432534, 65876586L))))

    // Exercise 3. Project an array of videos into an array of (id,title) using foreach
    def projectingSeq = {
      val idTitlePairs = new ListBuffer[(Long, String)]
      // --- INSERT CODE HERE ---
      // user foreach to accumulate (id, title) pairs from each video.
      // put the results into idTitlePairs using append() method.
      // example: idTitlePairs.append(newItem)
      ???
      // --- INSERT CODE HERE ---
      idTitlePairs.toSeq
    }

    //println(projectingSeq)

    // Exercise 4. Use map to project a sequence of videos into a sequence of (id, title) pairs
    def useMap = {
      // --- INSERT CODE HERE ---
      newReleases.map(???) // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(useMap)

    // Exercise 5. Use foreach() to collect only those videos with a rating of 5.0
    def filteringSeq = {
      val videos = new ListBuffer[Video]
      // --- INSERT CODE HERE ---
      // user foreach to accumulate video with a rating of 5.0
      ???
      // --- INSERT CODE HERE ---
      videos.toSeq
    }

    //println(filteringSeq)

    // Exercise 6. Chain filter and map to collect the ids of videos that have a rating of 5.0
    def useFilterAndMap = {
      // --- INSERT CODE HERE ---
      newReleases // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(useFilterAndMap)

    case class Category(name: String, videos: Seq[Video])

    val movieLists = Seq(
      Category(name = "New Releases",
        videos = Seq(
          Video(70111470L,
            "Die Hard",
            "http://cdn-0.nflximg.com/images/2891/DieHard.jpg",
            "http://api.netflix.com/catalog/titles/movies/70111470",
            4.0,
            Seq()),
          Video(654356453L,
            "Bad Boys",
            "http://cdn-0.nflximg.com/images/2891/BadBoys.jpg",
            "http://api.netflix.com/catalog/titles/movies/70111470",
            5.0,
            Seq(Bookmark(432534, 65876586L))))),
      Category(name = "Drama",
        videos = Seq(
          Video(65432445L,
            "The Chamber",
            "http://cdn-0.nflximg.com/images/2891/TheChamber.jpg",
            "http://api.netflix.com/catalog/titles/movies/70111470",
            4.0,
            Seq()),
          Video(675465L,
            "Fracture",
            "http://cdn-0.nflximg.com/images/2891/Fracture.jpg",
            "http://api.netflix.com/catalog/titles/movies/70111470",
            5.0,
            Seq(Bookmark(432534, 65876586L)))))
    )

    // Exercise 7. Flatten the movieLists sequence into a sequence of ids
    def flattenMovieLists = {
      val allVideoIdsinMovieLists = new ListBuffer[Long]
      // --- INSERT CODE HERE ---
      // use two nested foreach loops to flatten the movieLists into a sequence of video ids
      ???
      // --- INSERT CODE HERE ---
      allVideoIdsinMovieLists.toSeq
    }

    //println(flattenMovieLists)

    // Exercise 8. Use map and flatten to project and flatten the movieLists into a sequence of video ids
    def useMapAndFlatten = {
      // --- INSERT CODE HERE ---
      // use map and flatten the movieLists into a sequence of video ids.
      movieLists // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(useMapAndFlatten)

  }

  object SetB {

    import SetA.Bookmark

    case class BoxArt(width: Int, height: Int, url: String)

    case class Video(id: Long, title: String, boxarts: Seq[BoxArt], url: String, rating: Double, bookmark: Seq[Bookmark])

    case class Category(name: String, videos: Seq[Video])

    val movieLists = Seq(
      Category(name = "New Releases",
        videos = Seq(
          Video(70111470L,
            "Die Hard",
            Seq(BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"),
              BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/DieHard200.jpg")),
            "http://api.netflix.com/catalog/titles/movies/70111470",
            4.0,
            Seq()),
          Video(654356453L,
            "Bad Boys",
            Seq(BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/BadBoys200.jpg"),
              BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/BadBoys150.jpg")),
            "http://api.netflix.com/catalog/titles/movies/70111470",
            5.0,
            Seq(Bookmark(432534, 65876586L))))),
      Category(name = "Drama",
        videos = Seq(
          Video(65432445L,
            "The Chamber",
            Seq(BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/TheChamber150.jpg"),
              BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/TheChamber200.jpg")),
            "http://api.netflix.com/catalog/titles/movies/70111470",
            4.0,
            Seq()),
          Video(675465L,
            "Fracture",
            Seq(BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/Fracture200.jpg"),
              BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/Fracture150.jpg"),
              BoxArt(300, 200, "http://cdn-0.nflximg.com/images/2891/Fracture300.jpg")),
            "http://api.netflix.com/catalog/titles/movies/70111470",
            5.0,
            Seq(Bookmark(432534, 65876586L)))))
    )

    // Exercise 9. Retrieve id, title, and a 150x200 box art url for every video
    def retrieveIdTitleAndBoxArt = {
      // --- INSERT CODE HERE ---
      // use one or more map, filter, flatten calls to create a sequence with following items
      // List(
      //  (70111470,Die Hard,http://cdn-0.nflximg.com/images/2891/DieHard150.jpg),
      //  (654356453,Bad Boys,http://cdn-0.nflximg.com/images/2891/BadBoys150.jpg),
      //  (65432445,The Chamber,http://cdn-0.nflximg.com/images/2891/TheChamber150.jpg),
      //  (675465,Fracture,http://cdn-0.nflximg.com/images/2891/Fracture150.jpg))
      movieLists // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(retrieveIdTitleAndBoxArt)

    // Exercise 10. Use flatMap to retrieve id, title, and 150x200 box art url for every video
    def useFlatMap = {
      // --- INSERT CODE HERE ---
      // use one or more flatMap, map, filter calls to create a sequence with the following items
      // List(
      //  (70111470,Die Hard,http://cdn-0.nflximg.com/images/2891/DieHard150.jpg),
      //  (654356453,Bad Boys,http://cdn-0.nflximg.com/images/2891/BadBoys150.jpg),
      //  (65432445,The Chamber,http://cdn-0.nflximg.com/images/2891/TheChamber150.jpg),
      //  (675465,Fracture,http://cdn-0.nflximg.com/images/2891/Fracture150.jpg))
      movieLists // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(useFlatMap)
  }

  object SetC {

    import SetA.Bookmark
    import SetB._

    val boxArts = Seq(
      BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/Fracture200.jpg"),
      BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/Fracture150.jpg"),
      BoxArt(300, 200, "http://cdn-0.nflximg.com/images/2891/Fracture300.jpg"),
      BoxArt(425, 150, "http://cdn-0.nflximg.com/images/2891/Fracture425.jpg"))

    // Exercise 11. Use foreach to find the largest box art
    def findLargestArt = {
      var maxSize = -1
      var largest: BoxArt = null
      boxArts foreach { art =>
        val currentSize = art.width * art.height
        if (currentSize > maxSize) {
          largest = art
          maxSize = currentSize
        }
      }
      largest
    }

    //println(findLargestArt)

    // Exercise 12. Retrieve the largest rating
    def findLargestRating = {
      val ratings = Seq(2, 3, 1, 4, 5)
      // --- INSERT CODE HERE ---
      ratings.reduce(???) // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(findLargestRating)

    // Exercise 13. Retrieve url of the largest boxart
    def retrieveUrlOfLargest = {
      // --- INSERT CODE HERE ---
      boxArts // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(retrieveUrlOfLargest)

    // Exercise 14. Reducing with an initial value
    def idTitleMap = {
      case class Video(id: Long, title: String)
      val videos = Seq(
        Video(65432445L, "The Chamber"),
        Video(675465L, "Fracture"),
        Video(70111470L, "Die Hard"),
        Video(654356453L, "Bad Boys"))

      // expecting the following
      // Map(65432445 -> The Chamber, 675465 -> Fracture, 70111470 -> Die Hard, 654356453 -> Bad Boys)

      // --- INSERT CODE HERE ---
      // Map is immutable and update(k,v) method creates new map
      videos.foldLeft(Map.empty[Long, String])((map, video) => ???)
      // --- INSERT CODE HERE ---
    }

    // println(idTitleMap)

    // Exercise 15. Retrieve the id, title, and smallest box art url for every video
    def retrieveIdTitleSmallestBoxUrl = {
      val movieLists = Seq(
        Category(name = "New Releases",
          videos = Seq(
            Video(70111470L,
              "Die Hard",
              Seq(BoxArt(150, 200, "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"),
                BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/DieHard200.jpg")),
              "http://api.netflix.com/catalog/titles/movies/70111470",
              4.0,
              Seq()),
            Video(654356453L,
              "Bad Boys",
              Seq(BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/BadBoys200.jpg"),
                BoxArt(140, 200, "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg")),
              "http://api.netflix.com/catalog/titles/movies/70111470",
              5.0,
              Seq(Bookmark(432534, 65876586L))))),
        Category(name = "Drama",
          videos = Seq(
            Video(65432445L,
              "The Chamber",
              Seq(BoxArt(130, 200, "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"),
                BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/TheChamber200.jpg")),
              "http://api.netflix.com/catalog/titles/movies/70111470",
              4.0,
              Seq()),
            Video(675465L,
              "Fracture",
              Seq(BoxArt(200, 200, "http://cdn-0.nflximg.com/images/2891/Fracture200.jpg"),
                BoxArt(120, 200, "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"),
                BoxArt(300, 200, "http://cdn-0.nflximg.com/images/2891/Fracture300.jpg")),
              "http://api.netflix.com/catalog/titles/movies/70111470",
              5.0,
              Seq(Bookmark(432534, 65876586L)))))
      )

      // --- INSERT CODE HERE ---
      // use one or more flatMap, map, reduce
      movieLists // complete this expression
      // --- INSERT CODE HERE ---
    }

    //println(retrieveIdTitleSmallestBoxUrl)
  }

  def main(args: Array[String]): Unit = {
    SetA
    SetB
    SetC
  }
}

