
def group[A](as: Stream[A]): Stream[Stream[A]] =
  if (as.isEmpty) Stream()
  else { val pair = as.span(_ == as.head); pair._1 #:: group(pair._2) }

def ants = 
  Stream.iterate(Stream(1))(group(_).flatMap(g => Stream(g.head, g.length)))

println(ants(100000) drop 100000 take 10 mkString)

  
